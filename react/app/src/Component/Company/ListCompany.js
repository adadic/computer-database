import React, {useEffect, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {
    Table, TableBody, TableCell, TableContainer,
    TablePagination, TableRow, Paper
} from '@material-ui/core';
import EnhancedTableHead from "../Table/EnhancedTableHead";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import {stableSort, getComparatorCompany} from "../Table/TableFunction";
import {connect} from "react-redux";
import {getSearch} from "../../Store/Selector/SearchSelector";
import {newSearch, searchMode} from "../../Store/Action/SearchAction";
import Company from "./Company";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";

const useStyles = makeStyles((theme) => ({

    root: {
        width: '100%',
    },
    paper: {
        width: '28%',
        minWidth: 500,
        margin: "auto",
        marginBottom: theme.spacing(2),
    },
    table: {
        minHeight: 600,
        maxHeight: 750
    },
    visuallyHidden: {
        border: 0,
        clip: 'rect(0 0 0 0)',
        height: 1,
        margin: -1,
        overflow: 'hidden',
        padding: 0,
        position: 'absolute',
        top: 20,
        width: 1,
    },
}));

function ListCompany(props) {

    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [orderBy, setOrderBy] = useState("name");
    const [order, setOrder] = useState("asc");
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [selected, setSelected] = useState([]);
    const [companies, setCompanies] = useState(props.companies);
    const companySize = companies.filter(item => item.name && item.name.includes(props.search)).length;
    const emptyRows = companySize < 10 ? 10 - companySize % 10 : 0;
    const [addNew, setAddNew] = useState(false);
    const [newCompany, setNewCompany] = useState({
        companyId: companySize + 1,
        companyName: ""
    })

    useEffect(() => {
        props.changeMode(true);
        return function cleanup() {
            props.changeMode(false);
            props.newSearch("");
        }
    })
    useEffect(() => setCompanies(props.companies), [props.companies]);

    const handleRequestSort = (event, property) => {

        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = companies.map((n) => n.id);
            setSelected(newSelected);
            return;
        }
        setSelected([]);
    };

    const handleClick = (event, name) => {

        const selectedIndex = selected.indexOf(name);
        let newSelected = [];
        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selected, name);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selected.slice(1));
        } else if (selectedIndex === selected.length - 1) {
            newSelected = newSelected.concat(selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selected.slice(0, selectedIndex),
                selected.slice(selectedIndex + 1),
            );
        }
        setSelected(newSelected);
    };

    const handleChangePage = (event, newPage) => {

        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {

        setRowsPerPage(parseInt(event.target.value, 10));
        setPage(0);
    };

    const isSelected = (id) => selected.indexOf(id) !== -1;


    function deleteCompanies() {

        props.delete && selected.forEach(id => props.delete(id));
        setSelected([]);
    }

    const editCompany = (company) => {

        props.edit && props.edit(company);
    }

    const addCompany = () => {
        props.addCompany && props.addCompany(newCompany);
        setNewCompany({
            companyId: companySize + 2,
            companyName: ""
        })
        setAddNew(false);
    }

    return (

        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={selected.length} delete={deleteCompanies} mainTitle="Companies" addNew={setAddNew}/>
                <TableContainer className={classes.table}>
                    <Table className={classes.table} aria-labelledby="tableTitle" size="medium"
                           aria-label="enhanced table"
                    >
                        <EnhancedTableHead
                            classes={classes}
                            numSelected={selected.length}
                            order={order}
                            orderBy={orderBy}
                            onSelectAllClick={handleSelectAllClick}
                            onRequestSort={handleRequestSort}
                            rowCount={companySize}
                            headCells={props.headCells}
                        />
                        <TableBody style={{overflow: "auto"}}>
                            {addNew && <Company
                                key={newCompany.companyId}
                                labelId={`enhanced-table-checkbox-${newCompany.id}`}
                                handleClick={handleClick}
                                selected={selected}
                                row={newCompany}
                                edit={editCompany}
                                editMode={true}
                                addCompany={addCompany()}
                            />}
                            {stableSort(companies.filter(item => item.name && item.name.includes(props.search)), getComparatorCompany(order, orderBy))
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map(row => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${row.id}`;

                                    return (
                                        <Company
                                            key={row.id}
                                            isItemSelected={isItemSelected}
                                            labelId={labelId}
                                            handleClick={handleClick}
                                            selected={selected}
                                            row={row}
                                            edit={editCompany}
                                            editMode={false}
                                        />
                                    );
                                })}
                            {emptyRows > 0 && (
                                <TableRow style={{height: 80 * emptyRows}}>
                                    <TableCell colSpan={3}/>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 50]}
                    component="div"
                    count={companySize}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                    ActionsComponent={EnhancedTableFooter}
                />
            </Paper>
        </div>
    );
}

const mapStateToProps = (state) => {

    return {
        search: getSearch(state),
    };
}

const mapDispatchToProps = dispatch => {
    return {
        newSearch: search => dispatch(newSearch(search)),
        changeMode: mode => dispatch(searchMode(mode)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListCompany);
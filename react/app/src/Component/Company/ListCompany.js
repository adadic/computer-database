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
import {searchMode} from "../../Store/Action/SearchAction";
import Company from "./Company";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";
import {getOrder, getOrderBy, getPage, getRowsPerPage, getSelected} from "../../Store/Selector/PageSelector";
import {setOrder, setOrderBy, setPage, setRowsPerPage, setSelected} from "../../Store/Action/PageAction";

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
    useEffect(() => {
        props.changeMode(true);
        props.setOrder("asc");
        props.setOrderBy("company");
        props.setSelected([]);
        props.setPage(0);
        props.setRowsPerPage(10);

        return function cleanup() {
            props.changeMode(false);
        }
    })

    const [companies, setCompanies] = useState(props.companies);

    const handleRequestSort = (event, property) => {

        const isAsc = props.orderBy === property && props.order === 'asc';
        props.setOrder(isAsc ? 'desc' : 'asc');
        props.setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = companies.map((n) => n.id);
            props.setSelected(newSelected);
            return;
        }
        props.setSelected([]);
    };

    const handleClick = (event, name) => {

        const selectedIndex = props.selected.indexOf(name);
        let newSelected = [];
        if (selectedIndex === -1) {
            newSelected = newSelected.concat(props.selected, name);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(props.selected.slice(1));
        } else if (selectedIndex === props.selected.length - 1) {
            newSelected = newSelected.concat(props.selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                props.selected.slice(0, selectedIndex),
                props.selected.slice(selectedIndex + 1),
            );
        }
        props.setSelected(newSelected);
    };

    const handleChangePage = (event, newPage) => {

        props.setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {

        props.setRowsPerPage(parseInt(event.target.value, 10));
        props.setPage(0);
    };

    const isSelected = (id) => props.selected.indexOf(id) !== -1;

    const companySize = companies.filter(item => item.name && item.name.includes(props.search)).length;
    const emptyRows = companySize < 10 ? 10 - companySize % 10 : 0;

    function deleteCompanies() {

        props.delete && props.selected.forEach(id => props.delete(id));
        setSelected([]);
    }

    const editCompany = (company) => {

        props.edit && props.edit({data: company});
    }

    return (

        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={props.selected.length} delete={deleteCompanies} mainTitle="Companies"/>
                <TableContainer className={classes.table}>
                    <Table className={classes.table} aria-labelledby="tableTitle" size="medium"
                           aria-label="enhanced table"
                    >
                        <EnhancedTableHead
                            classes={classes}
                            numSelected={props.selected.length}
                            order={props.order}
                            orderBy={props.orderBy}
                            onSelectAllClick={handleSelectAllClick}
                            onRequestSort={handleRequestSort}
                            rowCount={companySize}
                            headCells={props.headCells}
                        />
                        <TableBody style={{overflow: "auto"}}>
                            {stableSort(companies.filter(item => item.name && item.name.includes(props.search)), getComparatorCompany(props.order, props.orderBy))
                                .slice(props.page * props.rowsPerPage, props.page * props.rowsPerPage + props.rowsPerPage)
                                .map(row => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${row.id}`;

                                    return (
                                        <Company
                                            key={row.id}
                                            isItemSelected={isItemSelected}
                                            labelId={labelId}
                                            handleClick={handleClick}
                                            selected={props.selected}
                                            row={row}
                                            edit={editCompany}
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
                    rowsPerPage={props.rowsPerPage}
                    page={props.page}
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
        order: getOrder(state),
        orderBy: getOrderBy(state),
        selected: getSelected(state),
        page: getPage(state),
        rowsPerPage: getRowsPerPage(state),
    };
}

const mapDispatchToProps = dispatch => {
    return {

        changeMode: mode => dispatch(searchMode(mode)),
        setOrder: order => dispatch(setOrder(order)),
        setOrderBy: orderBy => dispatch(setOrderBy(orderBy)),
        setSelected: selected => dispatch(setSelected(selected)),
        setPage: page => dispatch(setPage(page)),
        setRowsPerPage: rowsPerPage => dispatch(setRowsPerPage(rowsPerPage)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListCompany);
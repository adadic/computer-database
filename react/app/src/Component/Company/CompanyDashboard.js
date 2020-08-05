import React, {useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {
    Backdrop, CircularProgress, Paper,
    Table, TableBody, TableCell, TableContainer,
    TablePagination, TableRow
} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import EnhancedTableHead from "../Table/EnhancedTableHead";
import Company from "./Company";
import {getComparatorCompany, stableSort} from "../Table/TableFunction";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";
import {getSearch} from "../../Store/Selector/SearchSelector";
import {newSearch, searchMode} from "../../Store/Action/SearchAction";
import {connect} from "react-redux";

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

function CompanyDashboard(props) {

    const baseURL = 'http://localhost:8083/webapp/api';
    const [{data, loading, error}] = useAxios(baseURL + "/companies");
    const [companyList, setCompanyList] = useState(data);
    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'}
    ];
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [orderBy, setOrderBy] = useState("name");
    const [order, setOrder] = useState("asc");
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [selected, setSelected] = useState([]);
    const companySize = companyList ? companyList.filter(item => item.name && item.name.includes(props.search)).length : 0;
    const emptyRows = companySize < 10 ? 10 - companySize % 10 : 0;
    const [addNew, setAddNew] = useState(false);
    const [newCompany, setNewCompany] = useState({
        id: 0,
        name: ""
    });

    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'DELETE'
        },
        {manual: true}
    );

    const deleteCompany = () => {

        selected.forEach(element => {
            setCompanyList(companyList.filter(item => item.id !== element));
            return safeDelete(element);
        })
        setSelected([]);
    };

    const safeDelete = (id) => {

        executeDelete({url: `${baseURL}/companies/${id}`});
    };

    const editCompany = (company) => {

        executeEdit({data: company});
    };

    const addCompany = (company) => {

        executeAdd({data: company});
        setAddNew(false);
    };

    useEffect(() => {
        props.changeMode(true);
        return function cleanup() {
            props.changeMode(false);
            props.newSearch("");
        }
    }, []);
    useEffect(() => setCompanyList(data), [data, dataAdd, dataEdit]);

    const handleRequestSort = (event, property) => {

        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = companyList.map((n) => n.id);
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

    return (
        <div className="App">
            <img
            style={{
                position:'absolute',
                height:'100%',
                width:'100%',
                left:'0',
                top: '0',
                zIndex:'-1',
                opacity:'0.2'
            }}
            src='https://wallpaperaccess.com/full/1180986.jpg'
            />


            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <Backdrop open>
                    <CircularProgress color="inherit"/>
                </Backdrop>
                :
                <div className="table-size">
                    <Paper className={classes.paper}>
                        <EnhancedTableToolbar numSelected={selected.length} delete={deleteCompany}
                                              mainTitle="Companies" addNew={setAddNew}/>
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
                                    headCells={headCell}
                                />
                                <TableBody style={{overflow: "auto"}}>
                                    {addNew && <Company
                                        key={newCompany.companyId}
                                        labelId={`enhanced-table-checkbox-${newCompany.id}`}
                                        handleClick={handleClick}
                                        selected={selected}
                                        row={newCompany}
                                        edit={editCompany}
                                        delete={setAddNew}
                                        add={addCompany}
                                        addMode={true}
                                        addCompany={addCompany}
                                    />}
                                    {companyList && stableSort(companyList.filter(item => item.name && item.name.includes(props.search)), getComparatorCompany(order, orderBy))
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
                                                    delete={deleteCompany}
                                                    row={row}
                                                    edit={editCompany}
                                                    editMode={false}
                                                />
                                            );
                                        })}
                                    {emptyRows > 0 && (
                                        <TableRow style={{height: 50 * emptyRows}}>
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
            }
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

export default connect(mapStateToProps, mapDispatchToProps)(CompanyDashboard);

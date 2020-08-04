import React, {useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {
    Backdrop,
    CircularProgress, Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TablePagination,
    TableRow
} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import EnhancedTableHead from "../Table/EnhancedTableHead";
import {getComparatorCompany, stableSort} from "../Table/TableFunction";
import User from "./User";
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

function UserDashboard(props) {


    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'},
        {id: 'email', numeric: false, disablePadding: true, label: 'Email'},
        {id: 'role', numeric: false, disablePadding: true, label: 'Role'}
    ];
    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/users");
    const [userList, setUserList] = useState(data);
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [orderBy, setOrderBy] = useState("name");
    const [order, setOrder] = useState("asc");
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [selected, setSelected] = useState([]);
    const userSize = userList ? userList.filter(item => item.name && item.name.includes(props.search)).length : 0;
    const emptyRows = userSize < 10 ? 10 - userSize % 10 : 0;

    useEffect(() => {
        props.changeMode(true);
        return function cleanup() {
            props.changeMode(false);
            props.newSearch("");
        }
    }, []);

    const handleRequestSort = (event, property) => {

        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = userList.map((n) => n.id);
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


    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'DELETE'
        },
        {manual: true}
    );

    const deleteUser = (id) => {

        setUserList(userList.filter(item => item.id !== id));
        executeDelete({url: `${baseURL}/users/${id}`});
    }

    function addUser(computer){

        executeAdd({data:computer});
    }

    function editUser(computer){

        executeEdit({data: computer});
    }

    useEffect(() => setUserList(data), [data, dataAdd, dataEdit, userList]);

    return (
        <div className="App">
            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <Backdrop open>
                    <CircularProgress color="inherit" />
                </Backdrop>
                :
                <div className="table-size">
                    <Paper className={classes.paper}>
                        <EnhancedTableToolbar numSelected={selected.length} delete={deleteUser} mainTitle="Companies"/>
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
                                    rowCount={userSize}
                                    headCells={headCell}
                                />
                                <TableBody style={{overflow: "auto"}}>
                                    {userList && stableSort(userList.filter(item => item.name && item.name.includes(props.search)), getComparatorCompany(order, orderBy))
                                        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                        .map(row => {
                                            const isItemSelected = isSelected(row.id);
                                            const labelId = `enhanced-table-checkbox-${row.id}`;

                                            return (
                                                <User
                                                    key={row.id}
                                                    isItemSelected={isItemSelected}
                                                    labelId={labelId}
                                                    handleClick={handleClick}
                                                    selected={selected}
                                                    row={row}
                                                    edit={editUser}
                                                />
                                            );
                                        })}
                                    {emptyRows > 0 && (
                                        <TableRow style={{height: 80 * emptyRows}}>
                                            <TableCell colSpan={5}/>
                                        </TableRow>
                                    )}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <TablePagination
                            rowsPerPageOptions={[10, 25, 50]}
                            component="div"
                            count={userSize}
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

export default connect(mapStateToProps, mapDispatchToProps)(UserDashboard);

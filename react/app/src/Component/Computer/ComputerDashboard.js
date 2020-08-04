import React, {createRef, useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {
    Backdrop, CircularProgress, Paper,
    Table, TableBody, TableCell,
    TableContainer, TablePagination, TableRow
} from "@material-ui/core";
import AddComputer from "./AddComputer";
import {makeStyles} from "@material-ui/core/styles";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import EnhancedTableHead from "../Table/EnhancedTableHead";
import {getComparator, stableSort} from "../Table/TableFunction";
import Computer from "./Computer";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";
import {getSearch} from "../../Store/Selector/SearchSelector";
import {newSearch, searchMode} from "../../Store/Action/SearchAction";
import {connect} from "react-redux";

const useStyles = makeStyles((theme) => ({

    root: {
        width: '100%',
    },
    paper: {
        width: '100%',
        minWidth: 800,
        marginBottom: theme.spacing(2),
    },
    table: {
        minWidth: 750,
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

function ComputerDashboard(props) {

    const addMode = props.addMode;
    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'},
        {id: 'introduced', numeric: true, disablePadding: false, label: 'Introduced'},
        {id: 'discontinued', numeric: true, disablePadding: false, label: 'Discontinued'},
        {id: 'company', numeric: true, disablePadding: false, label: 'Company'},
    ];
    const baseURL = 'http://localhost:8083/webapp/api';
    const [{data, loading, error}] = useAxios(baseURL + "/computers");
    const [computerList, setComputerList] = useState(data);
    const classes = useStyles();
    const [page, setPage] = useState(0);
    const [order, setOrder] = useState("asc");
    const [orderBy, setOrderBy] = useState("computers");
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [selected, setSelected] = useState([]);
    const computerSize = computerList ? computerList.filter(item => item.name && item.name.includes(props.search)).length : 0;
    const emptyRows = computerSize < 10 ? 10 - computerSize % 10 : 0;

    useEffect(() => {
        props.changeMode(true);

        return function cleanup() {
            props.changeMode(false);
            props.newSearch("");
        }
    }, [])

    const handleRequestSort = (event, property) => {

        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = computerList.map((n) => n.id);
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
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/computers",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/computers",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/computers",
            method: 'DELETE'
        },
        {manual: true}
    );

    useEffect(() => setComputerList(data), [data, dataAdd, dataEdit]);

    const addComputer = (computer) => {

        executeAdd({data: computer});
    }

    const editComputer = (computer) => {

        executeEdit({data: computer});
    }

    const deleteComputer = (id) => {

        setComputerList(computerList.filter(item => item.id !== id));
        executeDelete({url: `${baseURL}/computers/${id}`});
    }

    return (
        <div className="App">
            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <Backdrop open ref={createRef()}>
                    <CircularProgress color="inherit"/>
                </Backdrop>
                :
                addMode
                    ?
                    <AddComputer addComputer={addComputer}/>
                    :
                    <div className="table-size">
                        <Paper className={classes.paper}>
                            <EnhancedTableToolbar numSelected={selected.length} delete={deleteComputer}
                                                  mainTitle="Computers"/>
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
                                        rowCount={computerSize}
                                        headCells={headCell}
                                    />
                                    <TableBody style={{overflow: "auto"}}>
                                        {computerList && stableSort(computerList.filter(item => item.name && item.name.includes(props.search)), getComparator(order, orderBy))
                                            .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                            .map(row => {
                                                const isItemSelected = isSelected(row.id);
                                                const labelId = `enhanced-table-checkbox-${row.id}`;

                                                return (
                                                    <Computer
                                                        key={row.id}
                                                        isItemSelected={isItemSelected}
                                                        labelId={labelId}
                                                        handleClick={handleClick}
                                                        selected={selected}
                                                        row={row}
                                                        edit={editComputer}
                                                    />
                                                );
                                            })
                                        }
                                        {emptyRows > 0 && (
                                            <TableRow style={{height: 53 * emptyRows}}>
                                                <TableCell colSpan={6}/>
                                            </TableRow>
                                        )}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                            <TablePagination
                                rowsPerPageOptions={[10, 25, 50]}
                                component="div"
                                count={computerSize}
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

export default connect(mapStateToProps, mapDispatchToProps)(ComputerDashboard);

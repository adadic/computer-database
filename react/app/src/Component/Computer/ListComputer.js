import React, {useEffect, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {
    Table, TableBody, TableCell, TableContainer,
    TablePagination, TableRow, Paper, Checkbox, Button
} from '@material-ui/core';
import EnhancedTableHead from "../Table/EnhancedTableHead";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import {stableSort, getComparator} from "../../Function/TableFunction";
import CreateIcon from '@material-ui/icons/Create';
import {connect} from "react-redux";
import {getSearch} from "../../Store/Selector/SearchSelector";
import {searchMode} from "../../Store/Action/SearchAction";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";

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

function ListComputer(props) {

    const classes = useStyles();
    useEffect(() => {
        props.changeMode(true);

        return function cleanup() {
            props.changeMode(false);
        }
    })
    const [computers, setComputers] = useState(props.computers);
    const [order, setOrder] = useState('asc');
    const [orderBy, setOrderBy] = useState('computers');
    const [selected, setSelected] = useState([]);
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);

    const handleRequestSort = (event, property) => {

        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = computers.map((n) => n.id);
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

    const computerSize = computers.filter(item => item.name && item.name.includes(props.search)).length;
    const emptyRows = computerSize < 10 ? 10 - computerSize % 10 : 0;

    function deleteComputers() {

        props.delete && selected.forEach(id => props.delete(id));
        setSelected([]);
    }

    function getDate(date) {

        const {year, monthValue, dayOfMonth} = date;
        return year + "-" + (monthValue < 10 ? "0" + monthValue : monthValue) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
    }

    const edit = (row) => (event) => {

        event.stopPropagation();
        console.log("I'm FREE");
        return false;
    }

    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={selected.length} delete={deleteComputers} mainTitle="Computers"/>
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
                            headCells={props.headCells}
                        />
                        <TableBody style={{overflow: "auto"}}>
                            {stableSort(computers.filter(item => item.name && item.name.includes(props.search)), getComparator(order, orderBy))
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map(row => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${row.id}`;

                                    return (
                                        <TableRow
                                            hover
                                            onClick={(event) => handleClick(event, row.id)}
                                            role="checkbox"
                                            aria-checked={isItemSelected}
                                            tabIndex={-1}
                                            key={row.id}
                                            selected={isItemSelected}
                                        >

                                            <TableCell padding="checkbox">
                                                <Checkbox
                                                    checked={isItemSelected}
                                                    inputProps={{'aria-labelledby': labelId}}
                                                />
                                            </TableCell>
                                            <TableCell padding="checkbox">
                                                <Button disabled={selected.length !== 0} onClick={edit(row)}>
                                                    <CreateIcon/>
                                                </Button>
                                            </TableCell>

                                            <TableCell component="th" id={labelId} scope="row" padding="none"
                                                       style={{width: '33%'}}>
                                                {row.name}
                                            </TableCell>
                                            {row.introduced
                                                ?
                                                <TableCell align="right">{getDate(row.introduced)}</TableCell>
                                                :
                                                <TableCell align="right"/>
                                            }
                                            {row.discontinued
                                                ?
                                                <TableCell align="right">{getDate(row.discontinued)}</TableCell>
                                                :
                                                <TableCell align="right"/>
                                            }
                                            <TableCell align="right"
                                                       style={{width: '20%'}}> {row.company.name}</TableCell>

                                        </TableRow>
                                    );
                                })}
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
    );
}

const mapStateToProps = (state) => {

    return {
        search: getSearch(state),
    };
}

const mapDispatchToProps = dispatch => {
    return {

        changeMode: mode =>
            dispatch(searchMode(mode))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(ListComputer);
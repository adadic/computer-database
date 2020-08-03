import React, {useEffect, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {
    Table, TableBody, TableCell, TableContainer,
    TablePagination, TableRow, Paper
} from '@material-ui/core';
import EnhancedTableHead from "../Table/EnhancedTableHead";
import EnhancedTableToolbar from "../Table/EnhancedTableToolbar";
import {stableSort, getComparator} from "../Table/TableFunction";
import {connect} from "react-redux";
import {getSearch} from "../../Store/Selector/SearchSelector";
import {searchMode} from "../../Store/Action/SearchAction";
import EnhancedTableFooter from "../Table/EnhancedTableFooter";
import { getOrder, getOrderBy, getSelected, getPage, getRowsPerPage } from '../../Store/Selector/PageSelector';
import { setOrder, setOrderBy, setSelected, setPage, setRowsPerPage } from "../../Store/Action/PageAction";

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

    const handleRequestSort = (event, property) => {

        const isAsc = props.orderBy === property && props.order === 'asc';
        props.setOrder(isAsc ? 'desc' : 'asc');
        props.setOrderBy(property);
    };

    const handleSelectAllClick = (event) => {

        if (event.target.checked) {
            const newSelected = computers.map((n) => n.id);
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

    const computerSize = computers.filter(item => item.name && item.name.includes(props.search)).length;
    const emptyRows = computerSize < 10 ? 10 - computerSize % 10 : 0;

    function deleteComputers() {

        props.delete && props.selected.forEach(id => props.delete(id));
        props.setSelected([]);
    }

    const editComputer = (computer) => {

        props.edit && props.edit({data: computer});
    }

    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={props.selected.length} delete={deleteComputers} mainTitle="Computers"/>
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
                            rowCount={computerSize}
                            headCells={props.headCells}
                        />
                        <TableBody style={{overflow: "auto"}}>
                            {stableSort(computers.filter(item => item.name && item.name.includes(props.search)), getComparator(props.order, props.orderBy))
                                .slice(props.page * props.rowsPerPage, props.page * props.rowsPerPage + props.rowsPerPage)
                                .map(row => {
                                    const isItemSelected = isSelected(row.id);
                                    const labelId = `enhanced-table-checkbox-${row.id}`;

                                    return (
                                        <Computer
                                            key={row.id}
                                            isItemSelected={isItemSelected}
                                            labelId={labelId}
                                            handleClick={handleClick}
                                            selected={props.selected}
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

export default connect(mapStateToProps, mapDispatchToProps)(ListComputer);
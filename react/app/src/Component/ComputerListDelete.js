import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import {Table, TableBody, TableCell, TableContainer,
    TablePagination, TableRow, Paper, Checkbox} from '@material-ui/core';
import EnhancedTableHead from "./EnhancedTableHead";
import EnhancedTableToolbar from "./EnhancedTableToolbar";

function descendingComparator(a, b, orderBy) {

    const first = a[orderBy];
    const second = b[orderBy];
    if(orderBy === "company") {
        if (!second["name"] && !first["name"]) {
            return 0;
        } else if (!second["name"]) {
            return 1;
        } else if (!first["name"]) {
            return -1;
        } else if (second["name"].toUpperCase() < first["name"].toUpperCase()) {
            return -1;
        } else if (second["name"].toUpperCase() > first["name"].toUpperCase()) {
            return 1;
        }
    } else {
        if (!second && !first) {
            return 0;
        }
        if (!second) {
            return 1;
        }
        if (!first) {
            return -1;
        }
        if (orderBy === "name") {
            if (second.toUpperCase() < first.toUpperCase()) {
                return -1;
            }
            if (second.toUpperCase() > first.toUpperCase()) {
                return 1;
            }
        } else {
            if (new Date(second.year, second.monthValue, second.dayOfMonth) < new Date(first.year, first.monthValue, first.dayOfMonth)) {
                return -1;
            }
            if (new Date(second.year, second.monthValue, second.dayOfMonth) > new Date(first.year, first.monthValue, first.dayOfMonth)) {
                return 1;
            }
        }
    }
    return 0;
}

function getComparator(order, orderBy) {

    return order === 'desc'
        ? (a, b) => descendingComparator(a, b, orderBy)
        : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {

    const stabilizedThis = array.map((el, index) => [el, index]);
    stabilizedThis.sort((a, b) => {
        const order = comparator(a[0], b[0]);
        if (order !== 0) return order;
        return a[1] - b[1];
    });
    return stabilizedThis.map((el) => el[0]);
}



const useStyles = makeStyles((theme) => ({

    root: {
        width: '100%',
    },
    paper: {
        width: '100%',
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

function ComputerListDelete(props) {

    const classes = useStyles();
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

    const emptyRows = rowsPerPage - Math.min(rowsPerPage, computers.length - page * rowsPerPage);

    function deleteComputers() {

        console.log({data: selected})
        console.log(selected[0] === 2)
        console.log(selected[0] === "2")
    }

    function getDate(date) {

        const {year, monthValue, dayOfMonth} = date;
        return year + "-" + (monthValue<10 ? "0" + monthValue : monthValue) + "-" + (dayOfMonth<10 ? "0" + dayOfMonth : dayOfMonth);
    }

    return (
        <div className={classes.root}>
            <Paper className={classes.paper}>
                <EnhancedTableToolbar numSelected={selected.length} delete={deleteComputers}/>
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
                            rowCount={computers.length}
                        />
                        <TableBody style={{ overflow: "auto" }}>
                            {stableSort(computers, getComparator(order, orderBy))
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
                                            <TableCell component="th" id={labelId} scope="row" padding="none" style={{width: '33%'}}>
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
                                            <TableCell align="right" style={{width: '20%'}}> {row.company.name}</TableCell>

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
                    count={computers.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onChangePage={handleChangePage}
                    onChangeRowsPerPage={handleChangeRowsPerPage}
                />
            </Paper>
        </div>
    );
}

export default ComputerListDelete;
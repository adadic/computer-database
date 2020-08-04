import React, {useEffect, useState} from "react";
import {
    Button, Checkbox, FormControl, InputLabel,
    MenuItem, Select, TableCell, TableRow, TextField
} from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";
import DoneIcon from "@material-ui/icons/Done";
import CloseIcon from "@material-ui/icons/Close";
import DateFnsUtils from "@date-io/date-fns";
import {KeyboardDatePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import useAxios from "axios-hooks";

function Computer(props) {


    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/companies");
    const [companyList, setCompanies] = useState(data);

    const [computer, setComputer] = useState({
        id: props.row.id,
        computerName: props.row.name,
        introduced: props.row.introduced ? getDate(props.row.introduced) : null,
        discontinued: props.row.discontinued ? getDate(props.row.discontinued) : null,
        companyId: props.row.company.id
    });

    useEffect(() => setCompanies(data),[data]);
    const [editMode, setEdit] = useState(false);

    function getDate(date) {

        const {year, monthValue, dayOfMonth} = date;
        return year + "-" + (monthValue < 10 ? "0" + monthValue : monthValue) + "-" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
    }

    const edit = () => (event) => {

        event.stopPropagation();
        setEdit(true);
    }

    const cancelEdit = () => () => {

        setComputer({
            id: props.row.id,
            computerName: props.row.name,
            introduced: props.row.introduced,
            discontinued: props.row.discontinued,
            companyId: props.row.company.id
        });
        setEdit(false);
    };

    const editComputer = () => () => {

        props.edit && props.edit(computer);
        setEdit(false);
    }

    const handleIntroduced = (date) => {
        try {
            setComputer({...computer, introduced: date.toISOString().slice(0, 10)})
        }
        catch (e) {
            setComputer({...computer, introduced: date})
        }
        console.log(computer.introduced)
    }

    const handleDiscontinued = (date) => {
        try {
            setComputer({...computer, discontinued: date.toISOString().slice(0, 10)})
        }
        catch (e) {
            setComputer({...computer, discontinued: date})
        }
    }

    return (
        editMode ?
            <TableRow hover key={computer.id}>

                <TableCell padding="checkbox">
                    <Button onClick={editComputer()}>
                        <DoneIcon/>
                    </Button>
                </TableCell>
                <TableCell padding="checkbox">
                    <Button onClick={cancelEdit()}>
                        <CloseIcon/>
                    </Button>
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '20%'}}>
                    <TextField
                        required
                        label="Computer Name"
                        value={computer.computerName}
                        onChange={(event) => setComputer({...computer, computerName: event.target.value})}
                    />
                </TableCell>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <TableCell align="right" style={{width: '25%'}}>
                        <KeyboardDatePicker
                            format="yyyy-MM-dd"
                            margin="normal"
                            id="date-picker-introduced"
                            label="Introduced Date"
                            minDate="1970-01-01"
                            maxDate="2038-01-18"
                            value={computer.introduced}
                            onChange={handleIntroduced}

                        />
                    </TableCell>
                    <TableCell align="right" style={{width: '25%'}}>
                        <KeyboardDatePicker
                            margin="normal"
                            id="date-picker-discontinued"
                            label="Discontinued Date"
                            format="yyyy-MM-dd"
                            value={computer.discontinued}
                            onChange={handleDiscontinued}
                            minDate={computer.introduced ? computer.introduced : "1970-01-01"}
                            maxDate="2038-01-18"
                            disabled={!computer.introduced || computer.introduced.toString() === "Invalid Date"}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                    </TableCell>
                </MuiPickersUtilsProvider>
                <TableCell align="right"
                           style={{width: '20%'}}>
                    <FormControl>
                        <InputLabel>Company</InputLabel>
                        <Select
                                value={computer.companyId}
                                onChange={(event) => setComputer({...computer, companyId: event.target.value})}
                        >
                            <MenuItem value={0}>None</MenuItem>
                            {companyList && companyList.map(company => {
                                return (
                                    <MenuItem key={company.id} value={company.id}>{company.name}</MenuItem>
                                )
                            })
                            }

                        </Select>
                    </FormControl>
                </TableCell>
            </TableRow>

            :

            <TableRow
                hover
                onClick={(event) => props.handleClick(event, props.row.id)}
                role="checkbox"
                aria-checked={props.isItemSelected}
                tabIndex={-1}
                key={props.row.id}
                selected={props.isItemSelected}
            >

                <TableCell padding="checkbox">
                    <Checkbox
                        checked={props.isItemSelected}
                        inputProps={{'aria-labelledby': props.labelId}}
                    />
                </TableCell>
                <TableCell padding="checkbox">
                    <Button disabled={props.selected.length !== 0} onClick={edit(props.row)}>
                        <CreateIcon/>
                    </Button>
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '33%'}}>
                    {props.row.name}
                </TableCell>
                {props.row.introduced
                    ?
                    <TableCell align="right">{getDate(props.row.introduced)}</TableCell>
                    :
                    <TableCell align="right"/>
                }
                {props.row.discontinued
                    ?
                    <TableCell align="right">{getDate(props.row.discontinued)}</TableCell>
                    :
                    <TableCell align="right"/>
                }
                <TableCell align="right"
                           style={{width: '20%'}}> {props.row.company.name}</TableCell>

            </TableRow>
    );
}

export default Computer;
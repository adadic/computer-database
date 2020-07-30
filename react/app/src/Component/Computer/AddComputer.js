import React, {useEffect, useState} from "react";
import useAxios from "axios-hooks";
import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import { TextField, makeStyles, Select, MenuItem } from "@material-ui/core";


const useStyle = makeStyles((theme) => ({
    root: {
        width: '100%'
    },

    picker: {
        minHeight: 60,
        maxHeight: 75,
        minWidth: 300,
    },

    textField: {
        minHeight: 60,
        maxHeight: 75,
        minWidth: 300,
    }
}))

function AddComputer() {

    const classes = useStyle();
    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/companies");
    const [companyList, setCompanies] = useState(data);

    const [computer, setComputer] = useState({
        name: "",
        introduced: "",
        discontinued: "",
        selectedCompany: "0"
    })

    useEffect(() => setCompanies(data),[data]);

    return (
        <form className={classes.root}>
            <div margin="auto">
                <TextField
                    className={classes.textField}
                    required
                    id="text-field-name"
                    label="Computer Name"
                    value={computer.name}
                    onChange={(event) => setComputer({...computer, name:event.target.value})}
                />
            </div>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <div>
                    <KeyboardDatePicker
                        className={classes.picker}
                        format="yyyy-MM-dd"
                        margin="normal"
                        id="date-picker-introduced"
                        label="Introduced Date"
                        minDate="1970-01-01"
                        maxDate="2038-01-18"
                        value="2020-12-20"
                        onChange={(event) => setComputer({...computer, introduced:event.target.value})}

                    />
                </div>
                <div>
                    <KeyboardDatePicker
                        className={classes.picker}
                        margin="normal"
                        id="date-picker-discontinued"
                        label="Discontinued Date"
                        format="yyyy-MM-dd"
                        value={computer.discontinued}
                        onChange={(event) => setComputer({...computer, discontinued:event.target.value})}
                        minDate="1970-01-01"
                        maxDate="2038-01-18"
                        KeyboardButtonProps={{
                            'aria-label': 'change date',
                        }}
                    />
                </div>
            </MuiPickersUtilsProvider>
            <div>
                <Select
                    value={computer.selectedCompany}
                    onChange={(event) => setComputer({...computer, selectedCompany:event.target.value})}
                >
                    <MenuItem value={0}>None</MenuItem>
                    {companyList && companyList.forEach(company => { return(
                        <MenuItem value={company.id}>company.name</MenuItem>
                    )})
                    }

                </Select>
            </div>
        </form>

    );

}

export default AddComputer;
import React, {useState} from "react";
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
    const companyList = useState(data);

    const [name, setName] = useState("");
    const [introducedDate, setIntroducedDate] = useState(new Date().getDate());
    const [discontinuedDate, setDiscontinuedDate] = useState(new Date().getDate());
    const [selectedCompany,setSelectedCompany] = useState("");

    return (

        <form className={classes.root}>
            <div margin="auto">
                <TextField
                    className={classes.textField}
                    required
                    id="text-field-name"
                    label="Computer Name"
                    defaultValue="Computer Name"
                    value={name}
                    onChange={(event) => setName(event.target.value)}
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
                        value={introducedDate}
                        onChange={(event) => setIntroducedDate(event.target.value)}

                    />
                </div>
                <div>
                    <KeyboardDatePicker
                        className={classes.picker}
                        margin="normal"
                        id="date-picker-discontinued"
                        label="Discontinued Date"
                        format="MM/dd/yyyy"
                        value={discontinuedDate}
                        onChange={(event) => setDiscontinuedDate(event.target.value)}
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
                    value={selectedCompany}
                    onChange={(event) => setSelectedCompany(event.target.value)}>
                    {companyList && companyList.forEach(company => console.log(company)
                    )}

                </Select>
            </div>
        </form>

    );

}

export default AddComputer;
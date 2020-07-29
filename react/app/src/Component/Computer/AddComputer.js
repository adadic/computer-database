import React from "react";
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
    const [companyList, setCompanyList] = React.useState(data);
    console.log(companyList);

    const [introducedDate, setIntroducedDate] = React.useState(new Date('2014-08-18T21:11:54'));
    const [discontinuedDate, setDiscontinuedDate] = React.useState(new Date('2014-08-18T21:11:54'));
    const [company, setCompany] = React.useState(companyList && companyList[0]);
    const handleIntroducedChange = (date) => {
        setIntroducedDate(date);
    };
    const handleDiscontinuedChange = (date) => {
        setDiscontinuedDate(date);
    };
    const handleCompanyChange = (company) => {
        setCompany(company);
    };
    return (

        <form className={classes.root}>
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <div margin="auto">
                    <TextField
                        className={classes.textField}
                        required
                        id="text-field-name"
                        label="Computer Name"
                        defaultValue="Computer Name"
                    />
                </div>
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
                        onChange={handleIntroducedChange}

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
                        onChange={handleDiscontinuedChange}
                        minDate="1970-01-01"
                        maxDate="2038-01-18"
                        KeyboardButtonProps={{
                            'aria-label': 'change date',
                        }}
                    />
                </div>
                <div>
                    <Select
                        value={company}
                        onChange={handleCompanyChange}>
                        {companyList && companyList.forEach((companie) =>
                            <MenuItem value={companie}>{companie && companie.name}</MenuItem>
                        )}

                    </Select>
                </div>
            </MuiPickersUtilsProvider>
        </form>

    );

}

export default AddComputer;
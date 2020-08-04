import React, {useEffect, useState} from "react";
import useAxios from "axios-hooks";
import DateFnsUtils from '@date-io/date-fns';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker,
} from '@material-ui/pickers';
import {useHistory} from "react-router-dom";
import {TextField, makeStyles, Select, MenuItem,
    FormControl, InputLabel, Button} from "@material-ui/core";
import "./AddComputer.scss";


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
    },
    select: {
        minHeight: 60,
        maxHeight: 75,
        minWidth: 300,
    },
    button: {
        marginTop: 10,
        marginLeft: 5,
        marginRight: 5,
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    }
}))

function AddComputer(props) {

    const [nameValid, setNameValid] = useState(false);
    const [introducedValid, setIntroducedValid] = useState(true);
    const [discontinuedValid, setDiscontinuedValid] = useState(true);
    const [fieldsValid, setFieldsValid] = useState(false)
    const history= useHistory();
    const classes = useStyle();
    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/companies");
    const [companyList, setCompanies] = useState(data);

    const [computer, setComputer] = useState({
        id:"0",
        computerName: "",
        introduced: null,
        discontinued: null,
        companyId:"0",
    })

    useEffect(() => setCompanies(data),[data]);

    const handleIntroduced = (date) => {
        if(date && date!==""){
            try {
                setComputer({...computer, introduced: date.toISOString().slice(0, 10)})
                setIntroducedValid(true)
                checkFields()
            }
            catch (e) {
                setComputer({...computer, introduced: date})
                setIntroducedValid(false)
                checkFields()
            }
        }
    }

    const handleDiscontinued = (date) => {
        if(date && date!==""){
            try {
                setComputer({...computer, discontinued: date.toISOString().slice(0, 10)})
                setDiscontinuedValid(true)
                checkFields()
            }
            catch (e) {
                setComputer({...computer, discontinued: date})
                setDiscontinuedValid(false)
                checkFields()
            }
        }
    }

    function checkName(name){
        if(name.length<=0){
            setNameValid(false)
        }
        else if(name.length>255){
            setNameValid(false)
        }
        else{
            setNameValid(true)
        }
    }

    function checkFields(){
        if(nameValid && introducedValid && discontinuedValid){
            setFieldsValid(true)
        }
        else{
            setFieldsValid(false)
        }
    }

    useEffect(() =>{
        checkName(computer.computerName)
        checkFields()
    }
    )


    return (
        <form className={classes.root}>
            <div margin="auto">
                <TextField
                    color={nameValid?"primary":"secondary"}
                    className={classes.textField}
                    required
                    id="text-field-name"
                    label="Computer Name"
                    value={computer.computerName}
                    onChange={(event) => setComputer({...computer, computerName: event.target.value})}
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
                        value={computer.introduced}
                        onChange={handleIntroduced}

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
                        onChange={handleDiscontinued}
                        minDate={computer.introduced ? computer.introduced : "1970-01-01"}
                        maxDate="2038-01-18"
                        disabled={!computer.introduced || computer.introduced.toString() === "Invalid Date"}
                        KeyboardButtonProps={{
                            'aria-label': 'change date',
                        }}
                    />
                </div>
            </MuiPickersUtilsProvider>
            <div>
                <FormControl className={classes.formControl}>
                    <InputLabel>Company</InputLabel>
                    <Select className={classes.select}
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
            </div>
            <Button 
            className={classes.button} 
            disabled={fieldsValid?false:true}
            href="/computers" 
            onClick={()=>{props.addComputer(computer)}} 
            variant="contained" 
            value="Ajouter" 
            color="primary">Ajouter</Button>
            <Button className={classes.button} onClick={()=> history.push("/computers")} variant="outlined" color="secondary">Annuler</Button>
        </form>
    );

}

export default AddComputer;
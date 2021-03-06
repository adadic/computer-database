import React, {useEffect, useState} from "react";
import {makeStyles} from '@material-ui/core/styles';
import {Button, Input} from "@material-ui/core";

import axios from 'axios';
import FormControl from "@material-ui/core/FormControl";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import clsx from "clsx";
import {Visibility, VisibilityOff} from "@material-ui/icons";
import * as qs from "qs";
import Alert from '@material-ui/lab/Alert';
import Collapse from "@material-ui/core/Collapse";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import CheckIcon from '@material-ui/icons/Check';
import ClearIcon from '@material-ui/icons/Clear';
import green from "@material-ui/core/colors/green";
import red from "@material-ui/core/colors/red";
import Paper from "@material-ui/core/Paper";


const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
            width: '80%',
            display: 'flex',
            flexWrap: 'wrap',
        },
        margin: {
            margin: theme.spacing(1),
        },
        withoutLabel: {
            marginTop: theme.spacing(3),
        },
        textField: {
            width: '75px',
        },
        alert: {
            width: '100%',
        },

    },
}));


function Register() {

    const classes = useStyles();

    const api = axios.create(
        {
            baseURL: 'http://localhost:8083/webapp/api/register'
        }
    );

    const config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    function displaySuccessAlert(){
        setDisplaySuccess(true);
        setTimeout(function(){ setDisplaySuccess(false);}, 2000);
    };

    const addUser = async () => {
        await api.post('', qs.stringify(user), config)
            .then((result) => {
                displaySuccessAlert();
                if (result.status === 200) {
                    setSuccess(true);
                    setMessage(result.data)

                }
            })
            .catch((error) => {
                displaySuccessAlert();
                if (error.response) {
                    /*
                     * The request was made and the server responded with a
                     * status code that falls out of the range of 2xx
                     */
                    setMessage(error.response.data);
                    setSuccess(false);
                    setUsernameAlreadyExistsError(true);


                } else if (error.request) {
                    /*
                     * The request was made but no response was received, `error.request`
                     * is an instance of XMLHttpRequest in the browser and an instance
                     * of http.ClientRequest in Node.js
                     */

                } else {
                    // Something happened in setting up the request and triggered an Error

                }
            });

    };

    const model = {
        userName: '',
        password: '',
        matchingPassword: '',
        email: ''
    };

    const [showPassword, setShowPassword] = useState(false);
    const [user, setUser] = useState(model);
    const [success, setSuccess] = useState(false);
    const [message, setMessage] = useState('');
    const [displayAlert, setDisplayAlert] = useState(false);
    const [checked, setChecked] = useState(false);
    const [passwordUpper, setPasswordUpper] = useState(false)
    const [passwordNumber, setPasswordNumber] = useState(false)
    const [passwordLength, setPasswordLength] = useState(false)
    const [passwordSpecial, setPasswordSpecial] = useState(false)
    const [passwordError, setPasswordError] = useState(true)
    const [matchingPasswordError, setMatchingPasswordError] = useState(true)
    const [emailError, setEmailError] = useState(true)
    const [usernameError, setUsernameError] = useState(true)
    const [usernameAlreadyExistsError, setUsernameAlreadyExistsError] = useState(false);
    const [displaySucess, setDisplaySuccess]=useState(false);

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const upper = new RegExp("(?=.*[A-Z])");
    const number = new RegExp("(?=.*[0-9])");
    const length = new RegExp("(?=.{8,})");
    const special = new RegExp("(?=.*[!@#$%^&*\\+])");

    const checkNumber = () => {
        if (number.test(user.password)) {
            setPasswordNumber(true);
        } else {
            setPasswordNumber(false);
            return false;
        }
        return true;
    };

    const checkLength = () => {

        if (length.test(user.password)) {
            setPasswordLength(true);

        } else {
            setPasswordLength(false);
            return false;
        }
        return true;
    };

    const checkUpper = () => {

        if (upper.test(user.password)) {
            setPasswordUpper(true);
        } else {
            setPasswordUpper(false);
            return false;
        }
        return true;
    };

    const checkSpecial = () => {

        if (special.test(user.password)) {
            setPasswordSpecial(true);

        } else {
            setPasswordSpecial(false);
            return false;
        }
        return true;
    };


    const passwordCheck = () => {

        if (checkUpper() & checkNumber() & checkLength() & checkSpecial()) {
            setPasswordError(false);
        } else {
            setPasswordError(true);
            setMessage("The password is invalid");
        }
    };

    const usernameCheck = () => {

        if (user.userName === '' || user.userName.includes(' ')) {
            setUsernameError(true);
            setMessage("The username cannot be empty or include space");
        } else {
            setUsernameError(false);
        }
    };


    const em2 = (e) => {

        setUser({...user, password: e.target.value});
    };

    const matchingPasswordCheck = () => {

        if (user.matchingPassword !== '' && user.password === user.matchingPassword) {
            setMatchingPasswordError(false);
        } else {
            setMatchingPasswordError(true);
            setMessage('The matching password is invalid');
        }
    };

    const emailCheck = () => {

        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(user.email)) {
            setEmailError(false);
        } else {
            setEmailError(true);
            setMessage('The email is invalid');
        }
    };

    const displayAlertError = () => {

        if (matchingPasswordError || passwordError || emailError || usernameError || usernameAlreadyExistsError) {
            setDisplayAlert(true);
        } else {
            setDisplayAlert(false);
        }
    };

    useEffect(() => {

        usernameCheck(user.userName);
        passwordCheck(user.password);
        matchingPasswordCheck(user.matchingPassword);
        emailCheck(user.email);
        displayAlertError();
    });

    return (
        <div className="Register" style={{width: '515px', margin:'auto'}}>
            <img
                style={{
                    position:'absolute',
                    height:'100%',
                    width:'100%',
                    left:'0',
                    top: '0',
                    zIndex:'-1',
                    opacity:'0.2'
                }}
                src='https://wallpaperaccess.com/full/1180986.jpg'
            />
            <Paper style={{padding:'10px', width:'auto'}}>
            <Collapse in={displaySucess}>
                <Alert className={clsx(classes.margin, classes.withoutLabel, classes.textField)}
                       severity={success ? "success" : "error"}>{message}</Alert>

            </Collapse>
            <form style={{width:'auto'}} >

                <FormControl style={{ display:'flex', flexDirection:'column'}} >
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-username">Username</InputLabel>
                        <Input
                            error={usernameError}
                            id="username"
                            type='text'
                            onChange={(e) => setUser({...user, userName: e.target.value.toLowerCase()})}
                        />
                    </FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-email">Email</InputLabel>
                        <Input
                            error={emailError}
                            id="email"
                            type='email'
                            onChange={(e) => {
                                setUser({...user, email: e.target.value})
                            }}
                        />
                    </FormControl>

                    <FormControl className={clsx(classes.margin, classes.textField)}>

                        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                        <Input
                            id="standard-adornment-password"
                            error={passwordError}
                            onFocus={() => setChecked(true)}
                            type={showPassword ? 'text' : 'password'}
                            value={user.password}
                            onChange={em2}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                    >
                                        {showPassword ? <Visibility/> : <VisibilityOff/>}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />

                        <Collapse in={checked} >
                            <ListItem style={{display:'flex', flexWrap:'wrap', margin:'auto', flexDirection:'space-between'}}>

                                <div style={{display:'flex', flexDirection:'row', justifyContent:'center',}}>
                                <ListItemText primary="Upper Case"/>
                                <ListItemIcon>

                                    {passwordUpper ? <CheckIcon style={{color: green[500]}}/> :
                                        <ClearIcon style={{color: red[500]}}/>}
                                </ListItemIcon>
                                </div>
                                <div style={{display:'flex', flexDirection:'row', justifyContent:'center'}}>
                                <ListItemText primary="Number"/>
                                <ListItemIcon>
                                    {passwordNumber ? <CheckIcon style={{color: green[500]}}/> :
                                        <ClearIcon style={{color: red[500]}}/>}
                                </ListItemIcon>
                                </div>
                                <div style={{display:'flex', flexDirection:'row', justifyContent:'center'}}>
                                <ListItemText primary="Min eight characters"/>
                                <ListItemIcon>
                                    {passwordLength ? <CheckIcon style={{color: green[500]}}/> :
                                        <ClearIcon style={{color: red[500]}}/>}
                                </ListItemIcon>
                                </div>

                                <div style={{display:'flex', flexDirection:'row', justifyContent:'center', margin:'auto'}}>
                                <ListItemText primary="One special character (!@#$%^&*+)"/>
                                <ListItemIcon>
                                    {passwordSpecial ? <CheckIcon style={{color: green[500]}}/> :
                                        <ClearIcon style={{color: red[500]}}/>}
                                </ListItemIcon>
                                </div>

                            </ListItem>
                        </Collapse>
                    </FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-password">Matching Password</InputLabel>
                        <Input
                            error={matchingPasswordError}
                            id="standard-adornment-matchingPassword"
                            type='password'
                            onChange={(e) => setUser({...user, matchingPassword: e.target.value})}
                        />
                    </FormControl>

                </FormControl>

            </form>

            <br/>
            <Button onClick={addUser} variant="outlined" color="primary">Add</Button>{' '}
            <a style={{textDecoration:'none'}} href={'register'}><Button  variant="outlined" color="secondary">Reset</Button></a>
        </Paper>
        </div>
    );
}

export default Register;
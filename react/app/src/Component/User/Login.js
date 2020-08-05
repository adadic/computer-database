import React from 'react';
import { useState } from "react";
import { connect } from 'react-redux';

import useAxios from 'axios-hooks';
import { Input, Button } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import { Visibility, VisibilityOff, LocalGasStationRounded } from "@material-ui/icons";
import { makeStyles } from '@material-ui/core/styles';
import { useHistory } from "react-router-dom";
import { setUser } from '../../Store/Action/UserAction';
import { getUser } from '../../Store/Selector/UserSelector';
import { getToken } from '../../Store/Selector/ConnexionSelector';
import { isConnected } from "../../Store/Action/ConnexionAction";

import clsx from "clsx";
import { setToken } from '../../Store/Action/ConnexionAction';
import Alert from "@material-ui/lab/Alert";
import Collapse from "@material-ui/core/Collapse";

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
            width: 200,
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
            width: '25ch',
        },
        alert: {
            width: '100%',
        }
    },
}));

export const baseURL = 'http://localhost:8083/webapp/api';

function Login(props) {

    const history = useHistory();
    const classes = useStyles();
    const [showPassword, setShowPassword] = useState(false);
    const [displaySuccess, setDisplaySuccess] = useState(false);
    const [message, setMessage] = useState('');
    const [success, setSuccess] = useState(false);

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };
    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    const [user, setUser] = useState({
        userName: "",
        email: "",
        roleName: "",
        password: ""
    });

    const [{ }, login] = useAxios(
        {
            url: `${baseURL}/login`,
            method: "POST"
        },
        { manual: true }
    );

    const [{ }, getUser] = useAxios(
        {
           method: "GET"
        },
        { manual: true }
    );

    function connexion() {
        login({ data: user })
            .then((res) => {
                displaySuccessAlert();
                if (res.status === 200) {
                    setSuccess(true);
                    setMessage(res.data)
                }
                props.seToken(res.data);
                localStorage.setItem('token', res.data);
                localStorage.setItem('isConnected', true);
                props.setConnected(true);
                props.setUser(user);
                localStorage.setItem('user', user.userName)
                props.closeDrawer();
                userGet();
                history.push("/computers")

            }).catch((error) => {
                displaySuccessAlert();
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    if (error.response.status === 401) {
                        setSuccess(false);
                        setMessage('Wrong Username or Password')
                    }
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
            });
    }

    const userGet = () => {
        getUser({
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            url: `${baseURL}/users/${localStorage.getItem('user')}`,
            data: props.user
        })
            .then(res => {
                console.log(res.data)
                props.setUser({
                    userName: res.data.user.username,
                    roleName: res.data.user.role.roleName,
                    email: res.data.user.email
                });
                props.state(true)
            }).catch((error) => {
                console.log(error.status)
            });
    }


    function displaySuccessAlert() {
        setDisplaySuccess(true);
        setTimeout(function () { setDisplaySuccess(false); }, 2000);
    }

    function register() {
        history.push("/register");
    }

    return (
        <div className="login">
            <Collapse in={displaySuccess}>
                <Alert className={clsx(classes.margin, classes.withoutLabel, classes.textField)}
                    severity={success ? "success" : "error"}>{message}</Alert>

            </Collapse>
            <form className={classes.root} noValidate autoComplete="off" method="POST">

                <FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-username">Username</InputLabel>
                        <Input
                            id="userName"
                            name="userName"
                            type='text'
                            onChange={(event) => setUser({ ...user, userName: event.target.value })}
                        />
                    </FormControl>

                    <FormControl className={clsx(classes.margin, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                        <Input
                            id="password"
                            name="password"
                            type={showPassword ? 'text' : 'password'}
                            onChange={(event) => setUser({ ...user, password: event.target.value })}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownPassword}
                                    >
                                        {showPassword ? <Visibility /> : <VisibilityOff />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>

                </FormControl>
            </form>
            <div className="buttonco">
            <Button onClick={connexion} variant="contained" color="primary">Connexion</Button>
            </div>
            <div className="buttonre">
            <Button variant="outlined" onClick={register} color="primary">Register</Button>
            </div>


        </div>
    );
}

const mapDispatchToProps = dispatch => {
    return {

        seToken: data => dispatch(setToken(data)),
        setConnected: conn => dispatch(isConnected(conn)),
        setUser: user => dispatch(setUser(user))
    }
}

const mapStateToProps = (state) => {
    return {

        token: getToken(state),
        user: getUser(state),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);
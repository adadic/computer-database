import React from 'react';
import { useState } from "react";
import { connect } from 'react-redux';

import useAxios from 'axios-hooks';
import { Input, Button } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import { Visibility, VisibilityOff } from "@material-ui/icons";
import { makeStyles } from '@material-ui/core/styles';
import { useHistory } from "react-router-dom";
import { setUser } from '../../Store/Action/UserAction';
import { getUser } from '../../Store/Selector/UserSelector';
import { getToken } from '../../Store/Selector/ConnexionSelector';
import { isConnected } from "../../Store/Action/ConnexionAction";

import clsx from "clsx";
import { setToken } from '../../Store/Action/ConnexionAction';

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

    function connexion() {
        login({ data: user })
            .then((res) => {
                props.seToken(res.data);
                props.setUser(user);
                props.setConnected(true);
                props.closeDrawer();
                history.push("/computers");
            }).catch((error) => {
                console.log(error);
            });
    }

    function register(){
        history.push("/register");
    }

    return (
        <div className="Register">
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
            <Button variant="outlined" onClick={connexion} color="primary">Connexion</Button>
            <Button variant="outlined" onClick={register} color="primary">Register</Button>
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
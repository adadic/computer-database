import React from 'react';
import {useState, useEffect} from "react";

import {Input, Button} from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import {Visibility, VisibilityOff} from "@material-ui/icons";
import FormHelperText from "@material-ui/core/FormHelperText";

function Login(){

    const [showPassword, setShowPassword] = useState(false);

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <div className="Register">
            <form noValidate autoComplete="off">

                <FormControl>
                    <FormControl>
                        <Input
                            id="standard-adornment-weight"
                            //endAdornment={<InputAdornment position="end">Kg</InputAdornment>}
                            aria-describedby="standard-weight-helper-text"
                            inputProps={{
                                'aria-label': 'weight',
                            }}
                        />
                        <FormHelperText id="standard-weight-helper-text">Username</FormHelperText>
                    </FormControl>

                    <FormControl>

                        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                        <Input
                            id="standard-adornment-password"
                            type={showPassword ? 'text' : 'password'}
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
                    </FormControl>
                </FormControl>

            </form>

            <Button href="/dashboard" variant="outlined" color="primary">Connexion</Button>{' '}
            <Button variant="outlined" color="secondary">Cancel</Button>
        </div>
    );
}

export default Login;
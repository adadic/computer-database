import React, {useState} from "react";
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
import Checkbox from "@material-ui/core/Checkbox";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Alert from '@material-ui/lab/Alert';


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


export function Register(props) {
    const classes = useStyles();

    // this.handleChangeName = event => {
    //     this.setState({ name: event.target.value });
    // }
    //
    // this.handleChangePassword = event => {
    //     this.setState({ password: event.target.value });
    // }
    //
    // this.handleSubmit = event => {
    //     event.preventDefault();
    //
    //     const user = {
    //         name: this.state.name,
    //         password: this.state.password
    //     };
    //
    //     axios.post(`http://localhost:8080/webapp/register`, { user })
    //         .then(res => {
    //             console.log(res);
    //             console.log(res.data);
    //         })
    // }


    // handleChange = event => {
    //   this.setUser({ name: event.target.value });

    // }

    const api = axios.create(
        {
            baseURL: 'http://localhost:8080/webapp/register'
        }
    )

    const config = {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }

    /*    const params = new URLSearchParams();
        params.append('userName', {userName});
        params.append('password', {password});
        params.append('matchingPassword', {matchingPassword});*/

//qs.stringify(user)


    let addUser = async () => {
        let response = await api.post('/', qs.stringify(user), config)
            .then((result) => {
                console.log("result " + result.statusText);
                if (result.status == 200) {
                    console.log("SUCCESSS");
                    //console.log(result.data)
                    setSuccess(true);
                } else if (result.status == 405) {
                    console.log("ERROR");
                    setSuccess(false);
                }
            })
            .catch((err) => {
                console.log(err);
            });
        console.log(response);
    }
    let model = {

        userName: '',
        password: '',
        matchingPassword: '',
    };


    const [showPassword, setShowPassword] = useState(false);
    const [user, setUser] = useState(model);
    const [success, setSuccess] = useState(false);

    //const [{data, loading, error}] = useAxios("http://localhost:8080/webapp/register");

    /* const [{data: dataAdd}, executeAdd] = useAxios(
         {
             url: 'http://localhost:8080/webapp/register',
             method: "POST"
         },
         {manual: true}
     );

     //const [addMode, setAddMode] = useState(false);
     useEffect(() => setUser(data), [data, dataAdd]);

     function addUser(user) {
         console.log("addUser" + JSON.stringify(user));
         //recipes.push(recipe);
         executeAdd({data: {...user}});
         //setAddMode(false);
     }
 */
    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

    return (
        <div className="Register">
            <Alert className={clsx(classes.margin, classes.withoutLabel, classes.textField)}
                   severity={success ? "success" : "error"}>New user added</Alert>
            <form className={classes.root} noValidate autoComplete="off">

                <FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-username">Username</InputLabel>
                        <Input
                            id="username"
                            type='text'
                            onChange={(e) => setUser({...user, userName: e.target.value})}
                        />
                    </FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-email">Email</InputLabel>
                        <Input
                            id="email"
                            type='email'
                            onChange={(e) => setUser({...user, email: e.target.value})}
                        />
                    </FormControl>

                    <FormControl className={clsx(classes.margin, classes.textField)}>

                        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
                        <Input
                            id="standard-adornment-password"
                            type={showPassword ? 'text' : 'password'}
                            value={user.password}
                            onChange={(e) => setUser({...user, password: e.target.value})}
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
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <InputLabel htmlFor="standard-adornment-password">Matching Password</InputLabel>
                        <Input
                            id="standard-adornment-matchingPassword"
                            type='password'
                            onChange={(e) => setUser({...user, matchingPassword: e.target.value})}
                        />
                    </FormControl>

                    {/*<Input type="textarea" rows="10"  onChange={(e)=>setUser({...user, name:e.target.value})}/>*/}
                    <FormControlLabel
                        control={<Checkbox color="primary" defaultChecked/>}
                        label={(<span>I accept the <a
                            href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></span>)}
                    />

                </FormControl>

            </form>


            <Button onClick={addUser} variant="outlined" color="primary">Add</Button>{' '}
            <Button onClick={addUser} variant="outlined" color="secondary">Cancel</Button>


            {/*<form onSubmit={this.handleSubmit}>
                <TextField id="standard-basic" label="username"  onChange={this.handleChangeName}  />
                <TextField id="standard-basic" label="password" onChange={this.handleChangePassword}  />
                <Button type="submit" outline color="primary">Add 2</Button>

            </form>


             <TextField error id="standard-error" label="Error" defaultValue="Hello World" />
             <TextField id="standard-basic" label="password" /> */}


        </div>
    );

}

export default React;
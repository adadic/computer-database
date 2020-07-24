import {useState, useEffect} from "react";
import React from "react";
import TextField from '@material-ui/core/TextField';
import {makeStyles} from '@material-ui/core/styles';
import useAxios from "axios-hooks";
import {Input, Button} from "@material-ui/core";

import axios from 'axios';
import FormControl from "@material-ui/core/FormControl";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from "@material-ui/core/IconButton";
import InputLabel from "@material-ui/core/InputLabel";
import clsx from "clsx";
import {Visibility, VisibilityOff} from "@material-ui/icons";
import FormHelperText from "@material-ui/core/FormHelperText";
import AlternateEmailIcon from '@material-ui/icons/AlternateEmail';


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


    let addUser = async () => {
        let response = await api.post('/', {user});
        console.log(response);
    }
    let model = {

        username: '',
        password: '',
        matchingPassword: '',
    };


    const [showPassword, setShowPassword] = useState(false);
    const [user, setUser] = useState(model);
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
            <form className={classes.root} noValidate autoComplete="off">

                <FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <Input
                            id="standard-adornment-weight"

                            onChange={(e) => setUser({...user, username: e.target.value})}
                            //endAdornment={<InputAdornment position="end">Kg</InputAdornment>}
                            aria-describedby="standard-weight-helper-text"
                            inputProps={{
                                'aria-label': 'weight',
                            }}
                        />
                        <FormHelperText id="standard-weight-helper-text">Username</FormHelperText>
                    </FormControl>
                    <FormControl className={clsx(classes.margin, classes.withoutLabel, classes.textField)}>
                        <Input
                            id="standard-adornment-weight"
                            type={'email'}
                            onChange={(e) => setUser({...user, email: e.target.value})}
                            endAdornment={<InputAdornment position="end">
                                <AlternateEmailIcon
                                    aria-label="toggle password visibility"


                                />
                            </InputAdornment>}
                            aria-describedby="standard-weight-helper-text"
                            inputProps={{
                                'aria-label': 'weight',
                            }}
                        />
                        <FormHelperText id="standard-weight-helper-text">Email</FormHelperText>
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
                        <Input
                            id="standard-adornment-weight"
                            type={'password'}
                            onChange={(e) => setUser({...user, matchingPassword: e.target.value})}
                            aria-describedby="standard-weight-helper-text"
                            inputProps={{
                                'aria-label': 'weight',
                            }}
                        />
                        <FormHelperText id="standard-weight-helper-text">Matching Password</FormHelperText>
                    </FormControl>

                    {/*<Input type="textarea" rows="10"  onChange={(e)=>setUser({...user, name:e.target.value})}/>*/}




                </FormControl>

            </form>

            <Button onClick={addUser} variant="outlined" color="primary">Add</Button>{' '}
            <Button onClick={addUser} variant="outlined" color="secondary">Cancel</Button>



            {/*<form onSubmit={this.handleSubmit}>*/}
            {/*    <TextField id="standard-basic" label="username"  onChange={this.handleChangeName}  />*/}
            {/*    <TextField id="standard-basic" label="password" onChange={this.handleChangePassword}  />*/}
            {/*    <Button type="submit" outline color="primary">Add 2</Button>*/}

            {/*</form>*/}


            {/* <TextField error id="standard-error" label="Error" defaultValue="Hello World" /> */}
            {/* <TextField id="standard-basic" label="password" /> */}


        </div>
    );

}

export default React;
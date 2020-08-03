import React, { useState } from 'react';
import {
    Button, Typography, Drawer,
    Toolbar, AppBar, InputBase
} from '@material-ui/core';
import { fade, makeStyles } from '@material-ui/core/styles';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import MenuBar from "./MenuBar";
import Login from "../User/Login";
import { useHistory } from "react-router-dom";
import SearchBar from "./SearchBar";
import { getToken, isConnected } from '../../Store/Selector/ConnexionSelector';
import { connect } from 'react-redux';
import ShowUser from '../User/ShowUser';
import useAxios from 'axios-hooks';
import { getUser } from '../../Store/Selector/UserSelector';
import { setUser } from '../../Store/Action/UserAction';
import { getSearchMode } from "../../Store/Selector/SearchSelector";

const baseURL = 'http://localhost:8083/webapp/api/users';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        marginBottom: theme.spacing(2)
    },
    menuButton: {
        marginRight: theme.spacing(2)
    },
    title: {
        flexGrow: 1,
        textAlign: "left"
    },
    search: {
        position: 'relative',
        borderRadius: theme.shape.borderRadius,
        backgroundColor: fade(theme.palette.common.white, 0.15),
        '&:hover': {
            backgroundColor: fade(theme.palette.common.white, 0.25),
        },
        marginLeft: 0,
        marginRight: 50,
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            marginLeft: theme.spacing(1),
            width: 'auto',
        },
    },
    searchIcon: {
        padding: theme.spacing(0, 2),
        height: '100%',
        position: 'absolute',
        pointerEvents: 'none',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    inputRoot: {
        color: 'inherit',
    },
    inputInput: {
        padding: theme.spacing(1, 1, 1, 0),
        // vertical padding + font size from searchIcon
        paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
        transition: theme.transitions.create('width'),
        width: '100%',
        [theme.breakpoints.up('sm')]: {
            width: '12ch',
            '&:focus': {
                width: '20ch',
            },
        },
    },
}));

function Header(props) {
    const classes = useStyles();
    const [state, setState] = useState(false);
    const history = useHistory();

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setState(open);
    };

    const closeDrawer = (event) => {
        setState(false);
    }

    const [user, setUser] = useState({
        userName: "",
        email: "",
        roleName: "",
        password: ""
    });

    const [{ }, getUser] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${props.token}`,
            },
            url: `${baseURL}/${props.user.userName}`,
            method: "GET"
        },
        { manual: true }
    );

    function userGet() {
        getUser({ data: user })
            .then((res) => {
            if(user.roleName === ""){
                setUser(user.roleName= res.data.user.role.roleName, user.userName= res.data.user.username);
                console.log(user)
                props.setUser(user);
                setState(true)
            }
            else {
                setState(true)
            }
            }).catch((error) => {
                console.log(error.status)
            });
    }

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <MenuBar className={classes.menuButton} />
                    <Typography variant="h6" className={classes.title}>
                        Computer Database
                    </Typography>

                    {props.isSearching && <SearchBar/>}
                    {props.token !== "" && props.isConnected
                        ?
                        <div>

                            <Button onClick={() => {userGet()}} color="inherit">
                                <AccountCircleIcon/>
                            </Button>

                            <Drawer an isConnectechor='right' open={state} onClose={toggleDrawer(false)}>
                                <ShowUser />
                            </Drawer>
                        </div>
                        :
                        <div>
                            <Button onClick={toggleDrawer(true)} color="inherit">Login</Button>
                            <Drawer anchor='right' open={state} onClose={toggleDrawer(false)}>
                                <Login closeDrawer={closeDrawer}/>
                            </Drawer>
                        </div>
                    }


                </Toolbar>
            </AppBar>
        </div>
    );
}

const mapStateToProps = (state) => {
    return {
        token: getToken(state),
        isConnected: isConnected(state),
        user: getUser(state),
        isSearching: getSearchMode(state)
    }
}

const mapDispatchToProps = dispatch => {
    return {

        setUser: user => dispatch(setUser(user))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);
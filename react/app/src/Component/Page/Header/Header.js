import React, { useState } from 'react';
import {
    Button, Typography, Drawer,
    Toolbar, AppBar, InputBase
} from '@material-ui/core';
import { fade, makeStyles } from '@material-ui/core/styles';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import MenuBar from "./MenuBar";
import Login from "../../User/Login";
import SearchBar from "./SearchBar";
import { getToken, isConnected } from '../../../Store/Selector/ConnexionSelector';
import { connect } from 'react-redux';
import ShowUser from '../../User/ShowUser';
import useAxios from 'axios-hooks';
import { getUser } from '../../../Store/Selector/UserSelector';
import { setUser } from '../../../Store/Action/UserAction';
import {getSearchMode} from "../../../Store/Selector/SearchSelector";

const baseURL = 'http://localhost:8083/webapp/api/users';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        marginBottom: theme.spacing(2),
        minWidth: 700
    },
    menuButton: {
        marginRight: theme.spacing(2)
    },
    title: {
        flexGrow: 1,
        textAlign: "left"
    },
    searchBar: {
        minWidth: 250
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

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }

        setState(open);
    };

    const closeDrawer = (event) => {
        setState(false);
    }

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <MenuBar className={classes.menuButton} />
                    <Typography variant="h6" className={classes.title}>
                        Computer Database
                    </Typography>

                    {props.isSearching && <SearchBar className={classes.searchBar}/>}
                    {props.token !== "" && props.isConnected
                        ?
                        <div>

                            <Button onClick={toggleDrawer(true)} color="inherit">
                                <AccountCircleIcon/>
                            </Button>

                            <Drawer anchor='right' open={state} onClose={toggleDrawer(false)}>
                                <ShowUser />
                            </Drawer>
                        </div>
                        :
                        <div>
                            <Button onClick={toggleDrawer(true)} color="inherit">Login</Button>
                            <Drawer anchor='right' open={state} onClose={toggleDrawer(false)}>
                                <Login closeDrawer={closeDrawer} state={state}/>
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

        setUser: user => dispatch(setUser(user)),
        
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);
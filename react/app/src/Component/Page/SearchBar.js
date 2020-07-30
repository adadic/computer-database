import React from "react";
import SearchIcon from "@material-ui/icons/Search";
import {InputBase} from "@material-ui/core";
import {fade, makeStyles} from "@material-ui/core/styles";
import {connect} from 'react-redux';
import {newSearch} from "../../Store/Action/SearchAction";

const useStyles = makeStyles((theme) => ({
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

function SearchBar(props){

    const classes = useStyles();

    return (
        <div className={classes.search}>
            <div className={classes.searchIcon}>
                <SearchIcon/>
            </div>
            <InputBase
                placeholder="Search…"
                classes={{
                    root: classes.inputRoot,
                    input: classes.inputInput,
                }}
                inputProps={{'aria-label': 'search'}}
                onChange={(e) => props.changeSearch(e.target.value)}
                value={props.search}
            />
        </div>
    );
}

const mapStateToProps = (state) => {

    return {
        search: state.search,
    }
}

const mapDispatchToProps = (dispatch) => {

    return {
        changeSearch: search =>
            dispatch(newSearch(search)),
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SearchBar);
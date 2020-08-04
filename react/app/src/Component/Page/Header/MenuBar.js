import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import {
    ListItemIcon, ListItemText,
    MenuItem, Menu, IconButton
} from '@material-ui/core';
import MenuIcon from "@material-ui/icons/Menu";
import GroupIcon from '@material-ui/icons/Group';
import { useHistory } from "react-router-dom";
import InfoIcon from '@material-ui/icons/Info';
import ComputerIcon from '@material-ui/icons/Computer';
import BusinessIcon from '@material-ui/icons/Business';
import HomeIcon from '@material-ui/icons/Home';
import { connect } from "react-redux";
import { getToken, isConnected } from "../../../Store/Selector/ConnexionSelector";
import { newSearch } from "../../../Store/Action/SearchAction";
import { getUser } from '../../../Store/Selector/UserSelector';

const StyledMenu = withStyles({
    paper: {
        border: '1px solid #d3d4d5',
    },
})((props) => (
    <Menu
        elevation={0}
        getContentAnchorEl={null}
        anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'center',
        }}
        transformOrigin={{
            vertical: 'top',
            horizontal: 'center',
        }}
        {...props}
    />
));

const StyledMenuItem = withStyles((theme) => ({
    root: {
        '&:focus': {
            backgroundColor: theme.palette.primary.main,
            '& .MuiListItemIcon-root, & .MuiListItemText-primary': {
                color: theme.palette.common.white,
            },
        },
    },
}))(MenuItem);

function MenuBar(props) {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const history = useHistory();

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const redirectTo = (path) => () => {

        props.changeSearch("");
        history.push(path)
        setAnchorEl(null);
    }

    return (
        <div>
            <IconButton edge="start"
                aria-controls="customized-menu"
                aria-haspopup="true"
                variant="contained"
                onClick={handleClick}
                color="inherit"
                aria-label="menu">
                <MenuIcon />
            </IconButton>
            <StyledMenu
                id="customized-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <StyledMenuItem onClick={redirectTo("/home")}>
                    <ListItemIcon>
                        <HomeIcon fontSize="small" />
                    </ListItemIcon>
                    <ListItemText primary="Home" />
                </StyledMenuItem>
                {(props.token !== "" && props.isConnected) &&
                    <StyledMenuItem onClick={redirectTo("/computers")}>
                        <ListItemIcon>
                            <ComputerIcon fontSize="small" />
                        </ListItemIcon>
                        <ListItemText primary="Computers" />
                    </StyledMenuItem>
                }
                {(props.token !== "" && props.isConnected) &&
                    <StyledMenuItem onClick={redirectTo("/companies")}>
                        <ListItemIcon>
                            <BusinessIcon fontSize="small" />
                        </ListItemIcon>
                        <ListItemText primary="Companies" />
                    </StyledMenuItem>
                }
                {(props.token !== "" && props.isConnected && props.user.roleName === "ADMIN") &&
                    <StyledMenuItem onClick={redirectTo("/users")}>
                        <ListItemIcon>
                            <GroupIcon fontSize="small" />
                        </ListItemIcon>
                        <ListItemText primary="Users" />
                    </StyledMenuItem>
                }
                <StyledMenuItem onClick={redirectTo("/about")}>
                    <ListItemIcon>
                        <InfoIcon fontSize="small" />
                    </ListItemIcon>
                    <ListItemText primary="About" />
                </StyledMenuItem>
            </StyledMenu>
        </div>
    );
}

const mapDispatchToProps = (dispatch) => {

    return {
        changeSearch: search =>
            dispatch(newSearch(search)),
    }
}

const mapStateToProps = (state) => {
    return {
        token: getToken(state),
        isConnected: isConnected(state),
        user: getUser(state)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MenuBar);
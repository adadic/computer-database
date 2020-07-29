import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import {ListItemIcon, ListItemText,
    MenuItem, Menu, IconButton} from '@material-ui/core';
import MenuIcon from "@material-ui/icons/Menu";
import { useHistory } from "react-router-dom";
import PowerSettingsNewIcon from '@material-ui/icons/PowerSettingsNew';
import InfoIcon from '@material-ui/icons/Info';
import ComputerIcon from '@material-ui/icons/Computer';
import BusinessIcon from '@material-ui/icons/Business';
import {newSearch} from "../../Store/Action/SearchAction";
import {connect} from "react-redux";

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
                <MenuIcon/>
            </IconButton>
            <StyledMenu
                id="customized-menu"
                anchorEl={anchorEl}
                keepMounted
                open={Boolean(anchorEl)}
                onClose={handleClose}
            >
                <StyledMenuItem onClick={redirectTo("/computers")}>
                    <ListItemIcon>
                        <ComputerIcon fontSize="small" />
                    </ListItemIcon>
                    <ListItemText primary="Computers" />
                </StyledMenuItem>
                <StyledMenuItem onClick={redirectTo("/companies")}>
                    <ListItemIcon>
                        <BusinessIcon fontSize="small" />
                    </ListItemIcon>
                    <ListItemText primary="Companies" />
                </StyledMenuItem>
                <StyledMenuItem onClick={redirectTo("/logout")}>
                    <ListItemIcon>
                        <PowerSettingsNewIcon fontSize="small" />
                    </ListItemIcon>
                    <ListItemText primary="Logout" />
                </StyledMenuItem>
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

export default connect(null, mapDispatchToProps)(MenuBar);
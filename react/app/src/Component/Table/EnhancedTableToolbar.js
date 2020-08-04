import {lighten, makeStyles} from "@material-ui/core/styles";
import {IconButton, Toolbar, Tooltip, Typography} from "@material-ui/core";
import clsx from "clsx";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from '@material-ui/icons/Add';
import {useHistory} from "react-router-dom";
import PropTypes from "prop-types";
import React from "react";

const useStyles = makeStyles((theme) => ({

    root: {
        paddingLeft: theme.spacing(2),
        paddingRight: theme.spacing(1),
    },
    highlight:
        theme.palette.type === 'light'
            ? {
                color: theme.palette.secondary.main,
                backgroundColor: lighten(theme.palette.secondary.light, 0.85),
            }
            : {
                color: theme.palette.text.primary,
                backgroundColor: theme.palette.secondary.dark,
            },
    title: {
        flex: '1 1 100%',
    },
}));

const EnhancedTableToolbar = (props) => {

    const classes = useStyles();
    const {numSelected, mainTitle} = props;
    const history = useHistory();

    return (
        <Toolbar
            className={clsx(classes.root, {
                [classes.highlight]: numSelected > 0,
            })}
        >
            {numSelected > 0 ? (
                <Typography className={classes.title} color="inherit" variant="subtitle1" component="div">
                    {numSelected} selected
                </Typography>
            ) : (
                <Typography className={classes.title} variant="h6" id="tableTitle" component="div">
                    {mainTitle}
                </Typography>
            )}

            {!props.user &&(numSelected > 0 ? (
                <Tooltip title="Delete" onClick={() => props.delete && props.delete()}>
                    <IconButton aria-label="delete">
                        <DeleteIcon/>
                    </IconButton>
                </Tooltip>
            ) : (
                <Tooltip title={"Add " + mainTitle} onClick={() => mainTitle === "Computers" ? history.push("/AddComputer") : mainTitle === "Users" ? history.push("/AddUser") : props.addNew && props.addNew(true)}>
                    <IconButton aria-label="Add">
                        <AddIcon/>
                    </IconButton>
                </Tooltip>
            ))}
        </Toolbar>
    );
};

EnhancedTableToolbar.propTypes = {

    numSelected: PropTypes.number.isRequired,
};

export default EnhancedTableToolbar;
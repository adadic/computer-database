import React, {useState} from "react";
import {Button, Checkbox, TableCell, TableRow, TextField} from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";
import DoneIcon from '@material-ui/icons/Done';
import CloseIcon from '@material-ui/icons/Close';

function User(props) {

    const [user, setUser] = useState({
        id: props.row.id,
        username: props.row.username,
        email: props.row.email,
        roleName: props.row.role.roleName,
    });
    const [editMode, setEdit] = useState(false);

    const edit = () => (event) => {

        event.stopPropagation();
        setEdit(true);
    }

    const cancelEdit = () => () => {

        setUser({...user, username: props.row.username});
        setEdit(false);
    };

    const editUser = () => () => {

        props.edit && props.edit(user);
        setEdit(false);
    }

    return (
        <TableRow
            hover
            tabIndex={-1}
            key={user.id}
            style={{height: 60}}
        >

            <TableCell padding="checkbox"/>

            <TableCell component="th" id={props.labelId} scope="row" padding="none"
                       style={{width: '35%', marginLeft: 10}}>
                {user.username}
            </TableCell>

            <TableCell component="th" id={props.labelId} scope="row" padding="none"
                       style={{width: '40%', marginLeft: 10}}>
                {user.email}
            </TableCell>

            <TableCell component="th" id={props.labelId} scope="row" padding="none"
                       style={{width: '75%', marginLeft: 10}}>
                {user.roleName}
            </TableCell>
        </TableRow>
    );
}

export default User;
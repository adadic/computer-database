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

    const cancelEdit = () => () =>{

        setUser({...user, username: props.row.username});
        setEdit(false);
    };

    const editUser = () => () => {

        props.edit && props.edit(user);
        setEdit(false);
    }

    return (
        editMode ?
            <TableRow hover key={user.id}>

                <TableCell padding="checkbox">
                    <Button onClick={editUser()}>
                        <DoneIcon/>
                    </Button>
                </TableCell>
                <TableCell padding="checkbox">
                    <Button onClick={cancelEdit()}>
                        <CloseIcon/>
                    </Button>
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '90%'}}>
                    <TextField
                        required
                        label="Username"
                        value={user.username}
                        style={{width: 200}}
                        onChange={(event) => setUser({...user, username: event.target.value})}
                    />
                </TableCell>
            </TableRow>

            :

            <TableRow
                hover
                onClick={(event) => props.handleClick(event, props.row.id)}
                role="checkbox"
                aria-checked={props.isItemSelected}
                tabIndex={-1}
                key={user.id}
                selected={props.isItemSelected}
            >

                <TableCell padding="checkbox">
                    <Checkbox
                        checked={props.isItemSelected}
                        inputProps={{'aria-labelledby': props.labelId}}
                    />
                </TableCell>
                <TableCell padding="checkbox">
                    <Button disabled={props.selected.length !== 0} onClick={edit()}>
                        <CreateIcon/>
                    </Button>
                </TableCell>

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
import React, {useState} from "react";
import {Button, Checkbox, TableCell, TableRow, TextField} from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";
import DoneIcon from '@material-ui/icons/Done';
import CloseIcon from '@material-ui/icons/Close';

function User(props) {

    const [user, setUser] = useState({
        userId: props.row.id,
        userName: props.row.name,
        userEmail: props.row.email,
        userRole: props.row.role,
    });
    const [editMode, setEdit] = useState(false);
    const [addMode, setAdd] = useState(user.id === 0);

    const edit = () => (event) => {

        event.stopPropagation();
        setEdit(true);
    }

    const cancelEdit = () => () =>{

        setUser({...user, companyName: props.row.name});
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
                           style={{width: '30%'}}>
                    <TextField
                        required
                        label="User Name"
                        value={user.userName}
                        style={{width: 200}}
                        onChange={(event) => setUser({...user, userName: event.target.value})}
                    />
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '30%'}}>
                    <TextField
                        required
                        label="Email"
                        value={user.userEmail}
                        style={{width: 200}}
                        onChange={(event) => setUser({...user, userEmail: event.target.value})}
                    />
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '30%'}}>
                    <TextField
                        required
                        label="Role"
                        value={user.userRole}
                        style={{width: 200}}
                        onChange={(event) => setUser({...user, userRole: event.target.value})}
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
                key={user.userId}
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
                           style={{width: '25%', marginLeft: 10}}>
                    {user.userName}
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '25%', marginLeft: 10}}>
                    {user.userEmail}
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '25%', marginLeft: 10}}>
                    {user.userRole}
                </TableCell>
            </TableRow>
    );
}

export default User;
import React, {useState} from "react";
import {TableCell, TableRow} from "@material-ui/core";

function User(props) {

    const [user, setUser] = useState({
        id: props.row.id,
        username: props.row.username,
        email: props.row.email,
        roleName: props.row.role.roleName,
    });

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
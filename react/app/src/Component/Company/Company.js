import React, {useState} from "react";
import {Button, Checkbox, TableCell, TableRow, TextField} from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";
import DoneIcon from '@material-ui/icons/Done';
import CloseIcon from '@material-ui/icons/Close';

function Company(props) {

    const [company, setCompany] = useState({
        id: props.row.id,
        name: props.row.name
    });
    const [editMode, setEdit] = useState(false);
    const [addMode, setAdd] = useState(company.id === 0);

    const edit = () => (event) => {

        event.stopPropagation();
        setEdit(true);
    }

    const cancelEdit = () => () =>{

        setCompany({...company, name: props.row.name});
        setEdit(false);
    };

    const editCompany = () => () => {

        props.edit && props.edit(company);
        setEdit(false);
    }

    return (
        editMode ?
            <TableRow hover key={company.id}>

                <TableCell padding="checkbox">
                    <Button onClick={editCompany()}>
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
                        label="Company Name"
                        value={company.name}
                        style={{width: 300}}
                        onChange={(event) => setCompany({...company, name: event.target.value})}
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
                key={company.id}
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
                           style={{width: '75%', marginLeft: 10}}>
                    {company.name}
                </TableCell>
            </TableRow>
    );
}

export default Company;
import React, {useState} from "react";
import {Button, Checkbox, TableCell, TableRow, TextField} from "@material-ui/core";
import CreateIcon from "@material-ui/icons/Create";
import DoneIcon from '@material-ui/icons/Done';
import CloseIcon from '@material-ui/icons/Close';

function Company(props) {

    const [company, setCompany] = useState({
        companyId: props.row.id,
        companyName: props.row.name
    });
    const [editMode, setEdit] = useState(props.editMode);

    const edit = () => (event) => {

        event.stopPropagation();
        setEdit(true);
    };

    const cancelEdit = () => () =>{

        setCompany({...company, companyName: props.row.name});
        setEdit(false);
    };

    const editCompany = () => () => {

        props.edit && props.edit(company);
        setEdit(false);
    };

    const removeCompany = () => () => {

        props.delete && props.delete(false);
        setEdit(false);
    };

    const addCompany = () => () => {

        props.add && props.add(company);
    };

    return (
        props.addMode ?
            <TableRow hover key={company.id}>

                <TableCell padding="checkbox">
                    <Button onClick={addCompany()} href="/companies">
                        <DoneIcon/>
                    </Button>
                </TableCell>
                <TableCell padding="checkbox">
                    <Button onClick={removeCompany()}>
                        <CloseIcon/>
                    </Button>
                </TableCell>

                <TableCell component="th" id={props.labelId} scope="row" padding="none"
                           style={{width: '90%'}}>
                    <TextField
                        required
                        label="Company Name"
                        value={company.companyName}
                        style={{width: 300}}
                        onChange={(event) => setCompany({...company, companyName: event.target.value})}
                    />
                </TableCell>
            </TableRow> :
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
                        value={company.companyName}
                        style={{width: 300}}
                        onChange={(event) => setCompany({...company, companyName: event.target.value})}
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
                key={company.companyId}
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
                    {company.companyName}
                </TableCell>
            </TableRow>
    );
}

export default Company;
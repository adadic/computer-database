import React, {createRef, useEffect, useState} from 'react';
import ListComputer from "./ListComputer";
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {Backdrop, CircularProgress} from "@material-ui/core";
import AddComputer from "./AddComputer";


function ComputerDashboard(props) {

    const addMode = props.addMode;
    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'},
        {id: 'introduced', numeric: true, disablePadding: false, label: 'Introduced'},
        {id: 'discontinued', numeric: true, disablePadding: false, label: 'Discontinued'},
        {id: 'company', numeric: true, disablePadding: false, label: 'Company'},
    ];

    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/computers");
    const [computerList, setComputerList] = useState(data);

    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            url: baseURL + "/computers",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            url: baseURL + "/computers",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            url: baseURL + "/computers",
            method: 'DELETE'
        },
        {manual: true}
    );

    

    useEffect(() => setComputerList(data),[data, dataAdd, dataEdit]);

    function addComputer(computer){
        setComputerList(computerList.add(computer))
        executeAdd({data:computer})
    }

    return (
        <div className="App">
            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <Backdrop open ref={createRef()}>
                    <CircularProgress color="inherit" />
                </Backdrop>
                :
                addMode
                    ?
                    <AddComputer addComputer={addComputer}/>
                    :
                    <div className="table-size">
                        {computerList && <ListComputer computers={computerList} edit={executeEdit} add={executeAdd} headCells={headCell}/>}
                    </div>
            }
        </div>
    );
}

export default ComputerDashboard;

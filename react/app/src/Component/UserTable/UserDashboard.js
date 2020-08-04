import React, {useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {Backdrop, CircularProgress} from "@material-ui/core";
import ListUser from "./ListUser";


function UserDashboard(props) {


    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'},
        {id: 'email', numeric: false, disablePadding: true, label: 'Email'},
        {id: 'role', numeric: false, disablePadding: true, label: 'Role'}
    ];

    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/users");
    const [userList, setUserList] = useState(data);

    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/users",
            method: 'DELETE'
        },
        {manual: true}
    );

    const deleteUser = (id) => {

        setUserList(userList.filter(item => item.id !== id));
        executeDelete({url: `${baseURL}/users/${id}`});
    }

    function addUser(computer){

        executeAdd({data:computer});
    }

    function editUser(computer){

        executeEdit({data: computer});
    }

    useEffect(() => setUserList(data), [data, dataAdd, dataEdit, userList]);

    return (
        <div className="App">
            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <Backdrop open>
                    <CircularProgress color="inherit" />
                </Backdrop>
                :
                <div className="table-size">
                    {userList && <ListUser users={userList} edit={editUser} add={addUser} headCells={headCell} delete={deleteUser}/>}
                </div>
            }
        </div>
    );
}

export default UserDashboard;

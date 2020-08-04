import React, {useEffect, useState}  from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {Backdrop, CircularProgress} from "@material-ui/core";
import ListUser from './ListUser';


function UserDashboard() {

        const headCell = [
            {id: 'name', numeric: false, disablePadding: true, label: 'Username'},
            {id: 'email', numeric: false, disablePadding: true, label: 'Email'},
            {id: 'role', numeric: false, disablePadding: true, label: 'Role'}
        ];
    
        const baseURL = 'http://localhost:8083/webapp/api';
        const [{ data, loading, error }] = useAxios(baseURL + "/users");
        const [userList, setUserList] = useState(data);
    
    
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

    
        useEffect(() => {
            setUserList(data);
        }, [data, dataEdit]);
    
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
                        {userList && <ListUser users={userList} edit={executeEdit} headCells={headCell}/>}
                    </div>
                }
            </div>
    );
}


export default UserDashboard;
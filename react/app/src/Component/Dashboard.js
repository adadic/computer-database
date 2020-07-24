import React, {useEffect, useState} from 'react';
import ComputerListDelete from "./ComputerListDelete";
import useAxios from "axios-hooks";
import CircularProgress from "@material-ui/core/CircularProgress";
import ErrorPage from "./ErrorPage";

export const baseURL = 'http://localhost:8083/webapp/api';

function Dashboard() {


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

    return (
        <div className="App">
            {error && <ErrorPage errorMessage=""/>}
            {loading
                ?
                <CircularProgress/>
                :
                <div className="table-size">
                    {computerList && <ComputerListDelete computers={computerList} edit={executeEdit} add={executeAdd}/>}
                </div>
            }
        </div>
    );
}

export default Dashboard;

import React, {useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {Backdrop, CircularProgress} from "@material-ui/core";
import ListCompany from "./ListCompany";


function CompanyDashboard() {


    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'}
    ];

    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/companies");
    const [companyList, setCompanyList] = useState(data);

    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            url: baseURL + "/companies",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            url: baseURL + "/companies",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            url: baseURL + "/companies",
            method: 'DELETE'
        },
        {manual: true}
    );

    useEffect(() => setCompanyList(data),[data, dataAdd, dataEdit]);

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
                    {companyList && <ListCompany companies={companyList} edit={executeEdit} add={executeAdd} headCells={headCell}/>}
                </div>
            }
        </div>
    );
}

export default CompanyDashboard;
import React, {useEffect, useState} from 'react';
import useAxios from "axios-hooks";
import ErrorPage from "../Page/ErrorPage";
import {Backdrop, CircularProgress} from "@material-ui/core";
import ListCompany from "./ListCompany";


function CompanyDashboard(props) {


    const headCell = [
        {id: 'name', numeric: false, disablePadding: true, label: 'Name'}
    ];

    const baseURL = 'http://localhost:8083/webapp/api';
    const [{ data, loading, error }] = useAxios(baseURL + "/companies");
    const [companyList, setCompanyList] = useState(data);

    const [{data: dataAdd}, executeAdd] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'POST'
        },
        {manual: true}
    );

    const [{data: dataEdit}, executeEdit] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'PUT'
        },
        {manual: true}
    );

    const [{}, executeDelete] = useAxios(
        {
            headers:{
                'Authorization' : `Bearer ${localStorage.getItem('token')}`,
            },
            url: baseURL + "/companies",
            method: 'DELETE'
        },
        {manual: true}
    );

    const deleteCompany = (id) => {

        setCompanyList(companyList.filter(item => item.id !== id));
        executeDelete({url: `${baseURL}/companies/${id}`});
    }

    const editCompany = (company) => {

        executeEdit({data: company});
    }

    const addCompany = (company) => {

        executeAdd({data: company});
    }

    useEffect(() => setCompanyList(data), [data, dataAdd, dataEdit]);

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
                    {companyList && <ListCompany companies={companyList} edit={editCompany} addCompany={addCompany} headCells={headCell} delete={deleteCompany}/>}
                </div>
            }
        </div>
    );
}

export default CompanyDashboard;

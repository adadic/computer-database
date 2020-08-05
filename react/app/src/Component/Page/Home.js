import React from 'react';
import {useHistory} from "react-router-dom";
import {Button} from "@material-ui/core";
import SaveIcon from '@material-ui/icons/Save';
import DashboardIcon from '@material-ui/icons/Dashboard';
import imageOne from './resources/imageOne.png'

const welcome = {
    fontSize: '500%',
    textAlign: 'center',
    color:'blue',
    margin: 'auto',
    marginTop: '15%',
    marginBottom: 50,
    minWidth: 750,
    maxWidth: 1000,

}

function Home(){

    const history = useHistory();

    return (
            <div >
                <img
                    style={{
                        position:'absolute',
                        height:'100%',
                        width:'100%',
                        left:'0',
                        top: '0',
                        zIndex:'-1',
                        opacity:'0.2'
                    }}
                    src='https://wallpaperaccess.com/full/1180986.jpg'
                />
                <p style={welcome}>Welcome to Computer Database</p>
                <div style={{minWidth: 750}}>
                    <a style={{textDecoration:'none'}}><Button onClick={() => history.push("/about")} variant="contained" color="primary" size='large' startIcon={<DashboardIcon />}>About</Button></a>{' '}
                    <a style={{textDecoration:'none'}}><Button onClick={() => history.push("/register")} variant="contained" size='large' color="primary" startIcon={<SaveIcon />}>Register</Button></a>
                </div>
            </div>
    );
}

export default Home;
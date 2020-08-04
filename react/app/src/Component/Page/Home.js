import React, {useState} from 'react';

import BackgroundSlider from 'react-background-slider'
import {Button} from "@material-ui/core";
import SaveIcon from '@material-ui/icons/Save';
import DashboardIcon from '@material-ui/icons/Dashboard';
import {useHistory} from "react-router-dom";
import { Redirect } from 'react-router'
import imageOne from './resources/imageOne.png'
import imageTwo from './resources/imageTwo.png'


const image1='https://c4.wallpaperflare.com/wallpaper/592/940/883/computers-history-marcin-osborne-wallpaper-preview.jpg'
const image2='https://i.pinimg.com/originals/7a/bd/12/7abd1279564c43a005ecddedd9d371fe.jpg'







const welcome = {
    fontSize: '500%',
    textAlign: 'center',
    color:'blue',
    backgroundColor:'rgba(0,0,0,0.2)',
}
function Home(){


    return (
        <wrapper>
        <BackgroundSlider
            images={[imageOne]}
            duration={100} transition={2} />
            <p style={welcome}>Welcome to Computer Database</p>
            <a style={{textDecoration:'none'}} href={'about'}><Button  variant="contained" color="primary" size='large' startIcon={<DashboardIcon />}>About</Button></a>{' '}
            <a style={{textDecoration:'none'}} href={'register'}><Button  variant="contained" size='large' color="primary" startIcon={<SaveIcon />}>Register</Button></a>
        </wrapper>

    );
}

export default Home;
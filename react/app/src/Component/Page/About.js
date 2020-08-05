import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import ProfileUi from 'react-profile-card';
import saad from './resources/saad.jpeg';
import andro from './resources/andro.jpg';
import yves from './resources/yves.jpg';
import julien from './resources/julien.jpg';
import BackgroundSlider from "react-background-slider";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        width: '70%',
        margin: "auto"
    },
}));

const styles = {
    text: {
        color: 'white',
        fontSize: '300%',
        backgroundColor: 'rgba(0,0,0,0.2)',
    },
}
const image1 = 'https://i.pinimg.com/originals/7a/bd/12/7abd1279564c43a005ecddedd9d371fe.jpg'
const image2 = 'https://wallpaperaccess.com/full/1180986.jpg'

function About() {
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <BackgroundSlider
                images={[image1, image2]}
                duration={100} transition={2}/>
            <p style={styles.text}>The Dev Team</p>

            <Grid container style={{flexDirection: "row", justifyContent: 'space-between'}}>
                <Grid item>

                    <ProfileUi
                        imgUrl={julien}
                        name='Julien'
                        designation='Developer'
                    />

                </Grid>
                <Grid item>

                    <ProfileUi style={{padding: 0}}
                               imgUrl={saad}
                               name='Saad'
                               designation='Developer'
                    />


                </Grid>

                <Grid item>

                    <ProfileUi
                        imgUrl={andro}
                        name='Andro'
                        designation='Developer'
                    />

                </Grid>

                <Grid item>

                    <ProfileUi
                        imgUrl={yves}
                        name='Yves'
                        designation='Developer'
                    />

                </Grid>
            </Grid>
        </div>
    );
}

export default About;
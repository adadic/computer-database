import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ProfileUi from 'react-profile-card';
import saad from './resources/saad.jpeg';


const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
}));
function About(){
    const classes = useStyles();
    return (
        <div className={classes.root}>
            <Grid container spacing={3}>
                <Grid item xs={3}>
                    <ProfileUi
                        imgUrl={saad}
                        name='Saad'
                        designation='Developer'
                    />
                </Grid>
                <Grid item xs={3}>

                        <ProfileUi
                            imgUrl={saad}
                            name='Saad'
                            designation='Developer'
                        />

                </Grid>
                <Grid item xs={3}>
                    <ProfileUi
                        imgUrl={saad}
                        name='Saad'
                        designation='Developer'
                    />
                </Grid>
                <Grid item xs={3}>
                    <ProfileUi
                        imgUrl={saad}
                        name='Saad'
                        designation='Developer'
                    />
                </Grid>
            </Grid>
        </div>
    );
}

export default About;
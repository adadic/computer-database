import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import ProfileUi from 'react-profile-card';
import saad from './resources/saad.jpeg';
import andro from './resources/andro.jpg';


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
            <Grid container spacing={10}>
                <Grid item>
                    <Paper>
                    <ProfileUi
                        imgUrl={saad}
                        name='Julien'
                        designation='Developer'
                    />
                    </Paper>
                </Grid>
                <Grid item>
                    <Paper >
                        <ProfileUi style={{padding:0}}
                            imgUrl={saad}
                            name='Saad'
                            designation='Developer'
                        />
                    </Paper>

                </Grid>

                <Grid item>
                    <Paper>
                    <ProfileUi
                        imgUrl={andro}
                        name='Andro'
                        designation='Developer'
                    />
                </Paper>
                </Grid>

                <Grid item>
                    <Paper>
                    <ProfileUi
                        imgUrl={saad}
                        name='Yves'
                        designation='Developer'
                    />
                        </Paper>
                </Grid>
            </Grid>
        </div>
    );
}

export default About;
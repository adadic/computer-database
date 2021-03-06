import React from 'react';
import { Button } from '@material-ui/core';
import { connect } from 'react-redux';
import { getUser } from '../../Store/Selector/UserSelector';
import { getToken } from '../../Store/Selector/ConnexionSelector';
import { useHistory } from "react-router-dom";
import PersonIcon from '@material-ui/icons/Person';
import MailIcon from '@material-ui/icons/Mail';
import AccessibilityIcon from '@material-ui/icons/Accessibility';
import "./ShowUser.scss"

function ShowUser(props) {

    const history = useHistory();

    const logout = () => {
        history.push("/logout")
    };

    return (
        <div className="user">
             <PersonIcon fontSize="small"/> Username : 
            <p className="text">{props.user.userName}</p>

            <MailIcon fontSize="small"/> Email : 
            <p className="text">{props.user.email}</p>

            <AccessibilityIcon fontSize="small"/> Rôle : 
            <p className="text">{props.user.roleName}</p>
        
            <Button variant="outlined" onClick={logout} color="secondary">Logout</Button>
        </div>
    );
}

const mapStateToProps = (state) => {

    return {
        user: getUser(state),
        token: getToken(state),
    }
}

export default connect(mapStateToProps, null)(ShowUser);
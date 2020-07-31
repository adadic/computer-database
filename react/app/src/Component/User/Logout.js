import React from 'react';
import { connect } from "react-redux"
import { getToken, isConnected } from '../../Store/Selector/ConnexionSelector';
import { getSearch } from '../../Store/Selector/SearchSelector';
import { getUser } from '../../Store/Selector/UserSelector';

function Logout(props){
    return (
        <div>{props.token + "----" + props.isConnected + "----" + props.search}
        
        <p>{props.user.username}</p>
        </div>
        
    );
}

const mapStateToProps = (state) => {
    return {
        token: getToken(state),
        search: getSearch(state),
        isConnected: isConnected(state),
        user: getUser(state)
    }  
}

export default connect(mapStateToProps, null)(Logout);
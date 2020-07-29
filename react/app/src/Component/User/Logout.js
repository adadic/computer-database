import React from 'react';
import { connect } from "react-redux"
import { getToken } from '../../Store/Selector/user.selector';

function Logout(props){
    return (
        <div>Logout Page</div>
        
    );
}

const mapStateToProps = (state) => {
    return {
        token: getToken(state),
    }  
}

export default connect(mapStateToProps, null)(Logout);
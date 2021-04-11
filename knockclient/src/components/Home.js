import React from 'react';
import {services} from '../components/Services/services';
import Service from './Services/Service';
import Display from './Layout/Display';

function Home() {
    return (
        <div className="mar">
            <Display/>
            <Service/>
        </div>
    )
}

export default Home

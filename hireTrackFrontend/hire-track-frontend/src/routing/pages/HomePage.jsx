import axios from 'axios';
import React, { useEffect, useState } from 'react'
import {useAuth} from '../contextApi/AuthProvider'
import { useNavigate } from 'react-router-dom';
import HireTrackCard from '../component/HireTrackCard'

const HomePage = () => {

    const {logout} = useAuth();

    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    }

    const [hireTracksData, sethireTracksData] = useState([]);

    const [error, setError] = useState("");

    const {accessToken} = useAuth();

    useEffect(() => {
        async function hireTrackCard() {
            console.log(accessToken);
            try {
                const res = await axios.get('http://localhost:8080/hireTrack/getAllHireTrackOfUser', {headers : {Authorization : `Bearer ${accessToken}`}});
                console.log(res.data);
                sethireTracksData(res.data);
            } catch (error) {
                if (error.response) {
                    setError(error.response.data || "Something went wrong. Please try again.");
                } else {
                    setError("Network error. Please try again.");
                }                
            }
        }
        hireTrackCard();
    },[]);
    
    return (
        <div>
            <nav className='homePageNav'>
                <div className='homePageNavLeft'>Hire Track</div>
                <div className='homePageNavRight'>
                    <div>Notes</div>
                    <div>Important Dates</div>
                    <div>About</div>
                    <div><button onClick={handleLogout}>Logout</button></div>
                </div>
            </nav>
            <main>
                <div className='homePageMain'>
                    <div className='homePagefilter'></div>
                    <div className='homePagecontent'>
                        {hireTracksData.map( x => <HireTrackCard key={x.id} eachHireTrackData={x} />)}
                    </div>
                </div>
                <div className='homePagePagination'><div>paging...</div></div>
            </main>
        </div>
    )
}

export default HomePage
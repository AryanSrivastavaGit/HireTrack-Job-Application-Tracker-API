import axios from 'axios';
import React, { useState } from 'react'

const HomePage = () => {

    const [hireTrackData, setHireTrackData] = useState({});

    // async function getHireTrackData(params) {
    //     try {
    //         const res = await axios.get();
    //     } catch (error) {
            
    //     }
    // }
    return (
        <div>
            <nav className='homePageNav'>
                <div className='homePageNavLeft'>Hire Track</div>
                <div className='homePageNavRight'>
                    <div>Notes</div>
                    <div>Important Dates</div>
                    <div>About</div>
                    <div>Logout</div>
                </div>
            </nav>
            <main>
                <div className='homePageMain'>
                    <div className='homePagefilter'></div>
                    <div className='homePagecontent'></div>
                </div>
                <div className='homePagePagination'><div>paging...</div></div>
            </main>
        </div>
    )
}

export default HomePage
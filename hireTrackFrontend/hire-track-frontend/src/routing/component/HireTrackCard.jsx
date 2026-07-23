import React from 'react'
import { useNavigate } from 'react-router-dom'

const HireTrackCard = ({ eachHireTrackData }) => {

    const navigate = useNavigate();

    const handleOpen = () => {
        navigate('/addHireTrack', {state : {eachHireTrackData}});
    }
    
    return (
        <div className='hireTrackCard'>
            <p>{eachHireTrackData.applicationStatus}</p>
            <p>{eachHireTrackData.importantDates[0] ? eachHireTrackData.importantDates[0].eventDate : ""}</p>
            <p>{eachHireTrackData.company ? eachHireTrackData.company.companyName : "unknown"}</p>
            <p>{eachHireTrackData.jobRole}</p>
            <p>{eachHireTrackData.jobType}</p>
            <button onClick={handleOpen}>OPEN</button>
        </div>
    )
}

export default HireTrackCard 
import React from 'react'

const HireTrackCard = ({eachHireTrackData}) => {
  return (
    <div className='hireTrackCard'>
        <div>
            <p>{eachHireTrackData.applicationStatus}</p>
            <p>{eachHireTrackData.importantDates[0] ? eachHireTrackData.importantDates[0].eventDate : ""}</p>
            <p>{eachHireTrackData.company ? eachHireTrackData.company.companyName : "unknown"}</p>
            <p>{eachHireTrackData.jobRole}</p>
            <p>{eachHireTrackData.jobType}</p>
            <button>OPEN</button>
        </div>
    </div>
  )
}

export default HireTrackCard
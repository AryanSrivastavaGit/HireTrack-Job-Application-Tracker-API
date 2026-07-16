import React from 'react'

const ImportantdatesCard = ({ eachImpDate }) => {
    return (
        <div>
            <div className='eventDate'>
                {"Date: " + eachImpDate.eventDate}
            </div>
            <div className='eventTitle'>
                {"Title: " + eachImpDate.eventTitle}
            </div>
        </div>
    )
}

export default ImportantdatesCard
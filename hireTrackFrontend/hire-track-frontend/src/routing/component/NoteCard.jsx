import React from 'react'

const NoteCard = ({ eachNote }) => {
  return (
    <div>
      <div className='title'>
        {"Title/Question: " + eachNote.title}
      </div>
      <div className='content'>
        {"Content/Answer: " + eachNote.content}
      </div>
    </div>
  )
}

export default NoteCard
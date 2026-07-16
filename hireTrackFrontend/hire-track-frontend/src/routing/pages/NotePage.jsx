import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useAuth } from '../contextApi/AuthProvider';
import NoteCard from '../component/NoteCard';

const NotePage = () => {

    const [notesData, setNotesData] = useState([]);

    const { accessToken } = useAuth();

    useEffect(() => {

        async function getNotes() {
            try {
                const res = await axios.get('http://localhost:8080/note/getNotesOfUser', {
                    headers: { Authorization: `Bearer ${accessToken}` }
                });
                console.log(res);
                setNotesData(res.data);

            } catch (error) {
                console.log(error);
            }
        }
        getNotes();

    }, [])


    return (
        <div>
            {notesData.map(x => <NoteCard key={x.id} eachNote={x} />)}
        </div>
    )
}

export default NotePage
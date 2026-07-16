import React, { useEffect, useState } from 'react'
import ImportantdatesCard from '../component/ImportantdatesCard';
import { useAuth } from '../contextApi/AuthProvider';
import axios from 'axios';

const ImportantDatePage = () => {

    const [importantDatesData, setImportantDatesData] = useState([]);

    const { accessToken } = useAuth();

    useEffect(() => {

        async function getImportantDates() {
            
            try {
                const res = await axios.get('http://localhost:8080/importantDate/getImportantDatesOfUser', {
                    headers: { Authorization: `Bearer ${accessToken}` }
                });

                setImportantDatesData(res.data);

            } catch (error) {
                console.log(error);
            }
        }
        getImportantDates();

    }, [])

    return (
        <div>
            {importantDatesData.map(x => <ImportantdatesCard key={x.id} eachImpDate={x} />)}
        </div>
    )
}

export default ImportantDatePage
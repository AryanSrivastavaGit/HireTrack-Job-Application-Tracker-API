import axios from 'axios';
import React, { useState } from 'react'
import { useAuth } from '../contextApi/AuthProvider';
import { useLocation, useNavigate } from 'react-router-dom';

const emptyHireTrackData = {
    appliedPortal: '',
    jobRole: '',
    jobType: '',
    roleDescription: '',
    minSalary: '',
    maxSalary: '',
    currency: '',
    applicationStatus: '',
    company: {
        companyName: '', companyUrl: '', country: '', state: '', city: '',
        district: '', street: '', landmark: '', building: '', postalCode: '',
    },
    companySources: [],
    roundDetails: [],
    importantDates: [],
    notes: []
};

const HireTrackPage = () => {

    const { state : locationState } = useLocation();
    const id = locationState?.eachHireTrackData?.id;
    const isEditMode = Boolean(id);
    console.log(isEditMode);

    const [hireTrackData, setHireTrackData] = useState(() => locationState?.eachHireTrackData ?? emptyHireTrackData);

    console.log(hireTrackData);

    const { accessToken } = useAuth();
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleHireTrackData = (e) => {
        const { name, value } = e.target;
        setHireTrackData(prev => ({ ...prev, [name]: value }));
        console.log(hireTrackData);
    }

    const handleCompanyData = (e) => {
        const { name, value } = e.target;
        setHireTrackData(prev => ({ ...prev, company: { ...prev.company, [name]: value } }));
        console.log(hireTrackData);
    }

    const addCompanySource = () => {
        setHireTrackData(prev => ({ ...prev, companySources: [...prev.companySources, { source: '', link: '' }] }));
    }

    const addRoundDetail = () => {
        setHireTrackData(prev => ({ ...prev, roundDetails: [...prev.roundDetails, { round: '', description: '', outcome: '' }] }));
    }

    const addImportantDate = () => {
        setHireTrackData(prev => ({ ...prev, importantDates: [...prev.importantDates, { eventDate: '', eventTitle: '', notifyAt: '' }] }));
    }

    const addNote = () => {
        setHireTrackData(prev => ({ ...prev, notes: [...prev.notes, { title: '', content: '' }] }));
    }

    const updateListItem = (listName, index, field, value) => {
        setHireTrackData(prev => {
            const updatedList = [...prev[listName]];       // copy the array
            updatedList[index] = { ...updatedList[index], [field]: value }; // copy the item, change one field
            return { ...prev, [listName]: updatedList };
        });
    }

    const removeListItem = (listName, index) => {
        setHireTrackData(prev => ({ ...prev, [listName]: prev[listName].filter((_, i) => i !== index) }));
    }

    const handleDelete = async () => {
        setLoading(true);
        try {
            const res = await axios.delete(`http://localhost:8080/hireTrack/deleteHireTrackOfUser/${id}`, { headers: { Authorization: `Bearer ${accessToken}` } });
            console.log(res.data);
            navigate('/homepage');
        } catch (error) {
            if (error.response) {
                setError(error.response.data || "Something went wrong. Please try again.");
            } else {
                setError("Network error. Please try again.");
            }
        } finally {
            setLoading(false);
        }
    }

    const clearHireTrackData = () => {
        setHireTrackData(emptyHireTrackData);
    }

    const submitHireTrackData = (e) => {

        e.preventDefault();

        setLoading(true);

        async function submitData() {
            try {

                const sanitizedData = {
                    ...hireTrackData,
                    roundDetails: hireTrackData.roundDetails.map(rd => ({
                        ...rd,
                        outcome: rd.outcome === '' ? null : rd.outcome
                    }))
                };

                if (isEditMode) {
                    const res = await axios.put(`http://localhost:8080/hireTrack/updateHireTrackOfUser/${id}`, sanitizedData,
                        { headers: { Authorization: `Bearer ${accessToken}` } }
                    );
                    console.log(res.data);
                } else {
                    const res = await axios.post('http://localhost:8080/hireTrack/addHireTrackOfUser', sanitizedData,
                        { headers: { Authorization: `Bearer ${accessToken}` } }
                    );
                    console.log(res.data);
                }

                navigate('/homepage');

            } catch (error) {
                if (error.response) {
                    setError(error.response.data || "Something went wrong. Please try again.");
                } else {
                    setError("Network error. Please try again.");
                }
            } finally {
                setLoading(false);
            }
        }
        submitData();
    }


    return (
        <div>
            <form onSubmit={submitHireTrackData}>

                <div>
                    <div>
                        <label htmlFor="companyName">Company Name*</label>
                        <input type="text" id='companyName' name='companyName' value={hireTrackData.company.companyName} onChange={handleCompanyData} required />
                    </div>
                    <div>
                        <label htmlFor="companyUrl">Company Url</label>
                        <input type="text" id='companyUrl' name='companyUrl' value={hireTrackData.company.companyUrl} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="country">country</label>
                        <input type="text" id='country' name='country' value={hireTrackData.company.country} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="state">State</label>
                        <input type="text" id='state' name='state' value={hireTrackData.company.state} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="city">City</label>
                        <input type="text" id='city' name='city' value={hireTrackData.company.city} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="district">District</label>
                        <input type="text" id='district' name='district' value={hireTrackData.company.district} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="street">Street</label>
                        <input type="text" id='street' name='street' value={hireTrackData.company.street} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="landmark">Landmark</label>
                        <input type="text" id='landmark' name='landmark' value={hireTrackData.company.landmark} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="building">Building</label>
                        <input type="text" id='building' name='building' value={hireTrackData.company.building} onChange={handleCompanyData} />
                    </div>
                    <div>
                        <label htmlFor="postalCode">Postal Code</label>
                        <input type="text" id='postalCode' name='postalCode' value={hireTrackData.company.postalCode} onChange={handleCompanyData} />
                    </div>
                </div>

                <div>
                    <label htmlFor="appliedPortal">Applied Portal</label>
                    <input type="text" id='appliedPortal' name='appliedPortal' value={hireTrackData.appliedPortal} onChange={handleHireTrackData} />
                </div>

                <div>
                    <label htmlFor="jobRole">Job Role*</label>
                    <input type="text" id='jobRole' name='jobRole' value={hireTrackData.jobRole} onChange={handleHireTrackData} required />
                </div>

                <div>
                    <label htmlFor="jobType">Job Type*</label>
                    <select name="jobType" id="jobType" onChange={handleHireTrackData} value={hireTrackData.jobType} required>
                        <option value="">OPTIONS</option>
                        <option value="Internship">Internship</option>
                        <option value="Internship-OnSite">Internship OnSite</option>
                        <option value="Internship-Remote">Internship Remote</option>
                        <option value="Internship-Hybrid">Internship Hybrid</option>
                        <option value="Training">Training</option>
                        <option value="Training-OnSite">Training OnSite</option>
                        <option value="Training-Remote">Training Remote</option>
                        <option value="Training-Hybrid">Training Hybrid</option>
                        <option value="Full-Time">Full Time</option>
                        <option value="Full-Time-OnSite">Full Time OnSite</option>
                        <option value="Full-Time-Remote">Full Time Remote</option>
                        <option value="Full-Time-Hybrid">Full Time Hybrid</option>
                        <option value="Part-Time">Part Time</option>
                        <option value="Part-Time-OnSite">Part Time OnSite</option>
                        <option value="Part-Time-Remote">Part Time Remote</option>
                        <option value="Part-Time-Hybrid">Part Time Hybrid</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="roleDescription">Role Description</label>
                    <textarea id='roleDescription' name='roleDescription' value={hireTrackData.roleDescription} onChange={handleHireTrackData} />
                </div>

                <div>
                    <label htmlFor="minSalary">Min Salary</label>
                    <input type="number" name="minSalary" id="minSalary" onChange={handleHireTrackData} value={hireTrackData.minSalary} />

                    <label htmlFor="maxSalary">Max Salary</label>
                    <input type="number" name="maxSalary" id="maxSalary" onChange={handleHireTrackData} value={hireTrackData.maxSalary} />

                    <label htmlFor="currency">Currency</label>
                    <select name="currency" id="currency" onChange={handleHireTrackData} value={hireTrackData.currency}>
                        <option value="">OPTIONS</option>
                        <option value="INR">INR</option>
                        <option value="USD">USD</option>
                        <option value="EUR">EUR</option>
                        <option value="JPY">JPY</option>
                        <option value="GBP">GBP</option>
                        <option value="AUD">AUD</option>
                        <option value="CAD">CAD</option>
                        <option value="CHF">CHF</option>
                        <option value="CNH">CNH</option>
                        <option value="HKD">HKD</option>
                        <option value="NZD">NZD</option>
                    </select>
                </div>

                <div>
                    <label htmlFor="applicationStatus">Application Status*</label>
                    <select name="applicationStatus" id="applicationStatus" onChange={handleHireTrackData} value={hireTrackData.applicationStatus} required>
                        <option value="">OPTIONS</option>
                        <option value="TRACKING">TRACKING</option>
                        <option value="APPLIED">APPLIED</option>
                        <option value="SCREENING">SCREENING</option>
                        <option value="ONLINE_ASSESSMENT">ONLINE ASSESSMENT</option>
                        <option value="INTERVIEW">INTERVIEW</option>
                        <option value="HR_ROUND">HR ROUND</option>
                        <option value="WAITING">WAITING</option>
                        <option value="OFFER">OFFER</option>
                        <option value="ACCEPTED">ACCEPTED</option>
                        <option value="REJECTED">REJECTED</option>
                        <option value="GHOSTED">GHOSTED</option>
                    </select>
                </div>

                <div>
                    <h3>Important Dates</h3>
                    {hireTrackData.importantDates.map((importantDate, index) => (
                        <div key={index}>
                            <label>Event Date</label>
                            <input type="date" value={importantDate.eventDate} onChange={(e) => updateListItem('importantDates', index, 'eventDate', e.target.value)} />
                            <label>Event Title</label>
                            <input type="text" value={importantDate.eventTitle} onChange={(e) => updateListItem('importantDates', index, 'eventTitle', e.target.value)} />
                            <label>Notify At</label>
                            <input type="datetime-local" value={importantDate.notifyAt} onChange={(e) => updateListItem('importantDates', index, 'notifyAt', e.target.value)} />
                            <button type="button" onClick={() => removeListItem('importantDates', index)}>Remove important date</button>
                        </div>
                    ))}
                    <button type="button" onClick={addImportantDate}>Add important date</button>
                </div>

                <div>
                    <h3>Round Details</h3>
                    {hireTrackData.roundDetails.map((roundDetail, index) => (
                        <div key={index}>
                            <label>Round</label>
                            <input type="text" value={roundDetail.round} onChange={(e) => updateListItem('roundDetails', index, 'round', e.target.value)} />
                            <label>Description</label>
                            <input type="text" value={roundDetail.description} onChange={(e) => updateListItem('roundDetails', index, 'description', e.target.value)} />
                            <label>Outcome</label>
                            <select value={roundDetail.outcome} onChange={(e) => updateListItem('roundDetails', index, 'outcome', e.target.value)}>
                                <option value="">OPTIONS</option>
                                <option value="PENDING">PENDING</option>
                                <option value="CLEARED">CLEARED</option>
                                <option value="REJECTED">REJECTED</option>
                            </select>
                            <button type="button" onClick={() => removeListItem('roundDetails', index)}>Remove Round Detail</button>
                        </div>
                    ))}
                    <button type="button" onClick={addRoundDetail}>Add Round Detail</button>
                </div>

                <div>
                    <h3>Company Sources</h3>
                    {hireTrackData.companySources.map((companySource, index) => (
                        <div key={index}>
                            <label>Source</label>
                            <input type="text" value={companySource.source} onChange={(e) => updateListItem('companySources', index, 'source', e.target.value)} />
                            <label>Link</label>
                            <input type="text" value={companySource.link} onChange={(e) => updateListItem('companySources', index, 'link', e.target.value)} />
                            <button type="button" onClick={() => removeListItem('companySources', index)}>Remove Company Source</button>
                        </div>
                    ))}
                    <button type="button" onClick={addCompanySource}>Add Company Source</button>
                </div>

                <div>
                    <h3>Notes</h3>
                    {hireTrackData.notes.map((note, index) => (
                        <div key={index}>
                            <label>Title</label>
                            <input type="text" value={note.title} onChange={(e) => updateListItem('notes', index, 'title', e.target.value)} />
                            <label>Content</label>
                            <textarea value={note.content} onChange={(e) => updateListItem('notes', index, 'content', e.target.value)} />
                            <button type="button" onClick={() => removeListItem('notes', index)}>Remove Note</button>
                        </div>
                    ))}
                    <button type="button" onClick={addNote}>Add Note</button>
                </div>

                <div>
                    <button type="button" onClick={clearHireTrackData}>Clear</button>
                </div>
                <div>
                    <button type='submit' disabled={loading}>{loading ? "Submitting..." : (isEditMode ? "Update" : "Submit")}</button>
                </div>
                {isEditMode && (
                    <div>
                        <button type="button" onClick={handleDelete} disabled={loading}>Delete</button>
                    </div>
                )}
            </form>
        </div>
    )
}

export default HireTrackPage
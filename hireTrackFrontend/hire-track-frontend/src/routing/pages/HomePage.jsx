import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useAuth } from '../contextApi/AuthProvider'
import { useNavigate } from 'react-router-dom';
import HireTrackCard from '../component/HireTrackCard'

const HomePage = () => {

    const { logout, accessToken } = useAuth();
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/');
    }

    const [hireTracksData, sethireTracksData] = useState([]);
    const [error, setError] = useState("");
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [filters, setFilters] = useState({
        applicationStatus: '',
        dateFrom: '',
        dateTo: '',
        companyName: '',
        jobRole: '',
        jobType: '',
        companyCountry: '',
        companyState: '',
        companyCity: '',
        minSalary: '',
        maxSalary: '',
        currency: ''
    });

    const handleFilter = (e) => {
        const { name, value } = e.target;
        setFilters(prev => ({ ...prev, [name]: value }));
        setPage(0);
    }

    const clearFilters = () => {
        setFilters({
            applicationStatus: '', dateFrom: '', dateTo: '', companyName: '', jobRole: '',
            jobType: '', companyCountry: '', companyState: '', companyCity: '',
            minSalary: '', maxSalary: '', currency: ''
        });
        setPage(0);
    };

    useEffect(() => {
        async function hireTrackCard() {
            try {

                const params = { page, size: 7 };

                Object.entries(filters).forEach(([key, value]) => {
                    if (value !== '') {
                        params[key] = value;
                    }
                });

                const res = await axios.get('http://localhost:8080/hireTrack/getAllHireTrackOfUser', {
                    headers: { Authorization: `Bearer ${accessToken}` },
                    params
                });
                
                sethireTracksData(res.data.content);
                setTotalPages(res.data.totalPages);

            } catch (error) {
                if (error.response) {
                    setError(error.response.data || "Something went wrong. Please try again.");
                } else {
                    setError("Network error. Please try again.");
                }
            }
        }
        hireTrackCard();
    }, [page, accessToken, filters]);

    const goToPage = (newPage) => {
        if (newPage >= 0 && newPage < totalPages) {
            setPage(newPage);
        }
    };

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
                    <div className='homePagefilter'>

                        <div>Hire Track Filters</div>

                        <div>
                            <label htmlFor="applicationStatus">Application Status</label>
                            <select name="applicationStatus" id="applicationStatus" onChange={handleFilter} value={filters.applicationStatus}>
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
                            <label htmlFor="">Date</label>
                            <label htmlFor="dateFrom">From</label>
                            <input type="date" name='dateFrom' id='dateFrom' onChange={handleFilter} value={filters.dateFrom} />
                            <label htmlFor="dateTo">To</label>
                            <input type="date" name='dateTo' id='dateTo' onChange={handleFilter} value={filters.dateTo} />
                        </div>

                        <div>
                            <label htmlFor="companyName">Company Name</label>
                            <input type="text" name='companyName' id='companyName' onChange={handleFilter} value={filters.companyName} />
                        </div>

                        <div>
                            <label htmlFor="jobRole">Job Role</label>
                            <input type="text" name='jobRole' onChange={handleFilter} value={filters.jobRole} />
                        </div>

                        <div>
                            <label htmlFor="jobType">Job Type</label>
                            <select name="jobType" id="jobType" onChange={handleFilter} value={filters.jobType}>
                                <option value="">OPTIONS</option>
                                <option value="Internship">Internship</option>
                                <option value="InternshipOnSite">Internship OnSite</option>
                                <option value="InternshipRemote">Internship Remote</option>
                                <option value="InternshipHybrid">Internship Hybrid</option>
                                <option value="FullTime">Full Time</option>
                                <option value="FullTimeOnSite">Full Time OnSite</option>
                                <option value="FullTimeOnRemote">Full Time Remote</option>
                                <option value="FullTimeOnHybrid">Full Time Hybrid</option>
                                <option value="PartTime">Part Time</option>
                                <option value="PartTimeOnSite">Part Time OnSite</option>
                                <option value="PartTimeRemote">Part Time Remote</option>
                                <option value="PartTimeHybrid">Part Time Hybrid</option>
                            </select>
                        </div>

                        <div>
                            <label htmlFor="">Company Location</label>
                            <label htmlFor="companyCountry">Country</label>
                            <input type="text" name='companyCountry' id='companyCountry' onChange={handleFilter} value={filters.companyCountry} />

                            <label htmlFor="companyState">State</label>
                            <input type="text" name='companyState' id='companyState' onChange={handleFilter} value={filters.companyState} />

                            <label htmlFor="companyCity">City</label>
                            <input type="text" name='companyCity' id='companyCity' onChange={handleFilter} value={filters.companyCity} />
                        </div>

                        <div>
                            <label htmlFor="">Salary</label>
                            <label htmlFor="minSalary">Min Salary</label>
                            <input type="number" name="minSalary" id="minSalary" onChange={handleFilter} value={filters.minSalary} />

                            <label htmlFor="maxSalary">Max Salary</label>
                            <input type="number" name="maxSalary" id="maxSalary" onChange={handleFilter} value={filters.maxSalary} />

                            <label htmlFor="currency">Currency</label>
                            <select name="currency" id="currency" onChange={handleFilter} value={filters.currency}>
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
                            <button onClick={clearFilters}>Clear</button>
                        </div>

                    </div>
                    <div className='homePagecontent'>
                        <div className='homePagecontentInfo'>
                            <p>Application Status</p>
                            <p>Important Date</p>
                            <p>Company Name</p>
                            <p>Job Role</p>
                            <p>Job Type</p>
                            <p>View Details</p>
                        </div>
                        {hireTracksData.map(x => <HireTrackCard key={x.id} eachHireTrackData={x} />)}
                    </div>
                </div>
                <div className='homePagePagination'>
                    <button onClick={() => goToPage(page - 1)} disabled={page === 0}>
                        Prev
                    </button>

                    {Array.from({ length: totalPages }, (_, i) => (
                        <button key={i} onClick={() => goToPage(i)} disabled={i === page}>
                            {i + 1}
                        </button>
                    ))}

                    <button onClick={() => goToPage(page + 1)} disabled={page === totalPages - 1}>
                        Next
                    </button>
                </div>
            </main >
        </div >
    )
}

export default HomePage
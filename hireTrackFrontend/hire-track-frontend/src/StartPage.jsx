import React from 'react'

const StartPage = () => {
  return (
    <div>
        <nav>
            <div className='startPageNav'>
                <div className='startPageLogo'>
                    <div>Hire Track</div>
                </div>
                <div className='startPageNavButton'>
                    <div>Login</div>
                    <div>Signup</div>
                </div>
            </div>
        </nav>
        <main>
            <div className='welcomeMsg'>
                <div><b>Welcome to hire track</b></div>
                <div><h1>Track every application, every stage, one place</h1></div>
                <div>Stop losing track of who you applied to and when. See your whole job search at a glance.</div>
            </div>
            <div className='tutorialArea'>
                <div className='tutorialAreaVideo'>vid</div>
                <div className='tutorialAreaMsg'>msg</div>
            </div>
            <div className='StartPageAbout'>
                <div>About</div>
                <div>Built by a job seeker who got tired of spreadsheets. Hire Track keeps every application, interview, and follow-up organized so nothing slips through the cracks.</div>
            </div>
        </main>
        <footer>
            <div>
                <div>Find me</div>
                <div>Questions or feedback? Reach out anytime.</div>
            </div>
            <div>
                <div>mail</div>
                <div>linkedIn</div>
                <div>Github</div>
            </div>
        </footer>
    </div>
  )
}

export default StartPage